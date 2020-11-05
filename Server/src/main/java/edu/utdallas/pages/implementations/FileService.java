package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.AccessLevel;
import edu.utdallas.pages.services.IDataSource;
import edu.utdallas.pages.services.IFileService;
import edu.utdallas.pages.services.IS3Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service("FileService")
public class FileService extends DbService implements IFileService {

    private static final String DEFAULT_PROFILE_PIC_KEY = "default_profile_pic.png";

    private final IS3Service s3Service;

    public FileService(@Qualifier("DataSource") IDataSource dataSource, @Qualifier("S3Service") IS3Service s3Service) {
        super(dataSource);
        this.s3Service = s3Service;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadClip(String user, String name, byte[] fileData) {
        String uuid = getUUID();
        s3Service.uploadFile(uuid,fileData);
        query(getQuery("INSERT_CLIP"),uuid,user,name,getTime(), AccessLevel.VIEW.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadPic(String user, byte[] fileData) {
        String uuid = getUUID();
        s3Service.uploadFile(uuid,fileData);
        query(getQuery("UPDATE_PIC"),uuid,user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrievePicKey(String user) {
        return retrieve("pic_key",getQuery("RETRIEVE_PIC"),user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] retrievePicData(String user) {
        String path = retrievePicKey(user);
        if(!path.equals("")) {
            return s3Service.retrieveFile(path);
        } else {
            return s3Service.retrieveFile(DEFAULT_PROFILE_PIC_KEY);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveClipKey(String user, String name) {
        return retrieve("clip_key",getQuery("RETRIEVE_CLIP"),user,name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public byte[] retrieveClipData(String user, String name) {
        String path = retrieveClipKey(user,name);
        if(!path.equals("")) {
            return s3Service.retrieveFile(path);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public byte[] retrieveClipData(String clipKey) {
        return s3Service.retrieveFile(clipKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteClip(String key) {
        query(getQuery("DISOWN_CLIP"),Reserved.Anonymous.getText(),key);
    }

}
