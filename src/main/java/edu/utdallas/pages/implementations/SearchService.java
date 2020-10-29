package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.IDataSource;
import edu.utdallas.pages.services.ISearchService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("SearchService")
public class SearchService extends DbService implements ISearchService {

    public static final int ELEMENT_PER_PAGE = 20;

    public SearchService(@Qualifier("DataSource") IDataSource dataSource) {
        super(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSearchResults(String query, int first, int last) {
        String[] columns = {"id","type","name","user","match_score"};
        Connection conn = getDataSource().getConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(getQuery("SEARCH_QUERY"));
                for(int i = 0; i < 12; i++) {
                    ps.setString(i+1, query);
                }
                ps.setInt(13, first);
                ps.setInt(14, last);
                ResultSet rs = ps.executeQuery();
                JSONArray arr = new JSONArray();
                while(rs.next()) {
                    JSONObject jsonObject = new JSONObject();
                    for(int i = 0; i < 4; i++) {
                        String col = rs.getString(columns[i]);
                        jsonObject.put(columns[i], Objects.requireNonNullElse(col, ""));
                    }
                    arr.put(jsonObject);
                }
                rs.close();
                JSONObject obj = new JSONObject();
                obj.put("result",arr);
                return obj.toString();
            } catch (SQLException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } finally {
                getDataSource().closeStatement(ps);
                getDataSource().closeConnection(conn);
            }
        }
        return new JSONArray().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSearchResults(String query, int page) {
        if(page < 1) {
            return new JSONArray().toString();
        }
        int last = page * ELEMENT_PER_PAGE;
        int first = last - ELEMENT_PER_PAGE;
        return getSearchResults(query, first, last);
    }
}
