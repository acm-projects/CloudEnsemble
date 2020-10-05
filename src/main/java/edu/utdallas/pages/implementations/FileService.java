package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.IDataSource;
import edu.utdallas.pages.services.IFileService;
import edu.utdallas.pages.services.IS3Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service("FileService")
public class FileService implements IFileService {

    private final IDataSource dataSource;
    private final IS3Service s3Service;

    public FileService(@Qualifier("DataSource") IDataSource dataSource, @Qualifier("S3Service") IS3Service s3Service) {
        this.dataSource = dataSource;
        this.s3Service = s3Service;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadClip(String user, String name, byte[] fileData) {
        String uuid = Database.getUUID().toString();
        //Upload to S3 Bucket
        s3Service.uploadFile(uuid,fileData);
        //Insert in database
        Database.query(dataSource,dataSource.getQuery("INSERT_CLIP"),uuid,user,name,Database.getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadPic(String user, byte[] fileData) {
        String uuid = Database.getUUID().toString();
        //Upload to S3 Bucket
        s3Service.uploadFile(uuid,fileData);
        //Insert in database
        Database.query(dataSource,dataSource.getQuery("ADD_PIC"),uuid,user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrievePicKey(String user) {
        return Database.retrieve(dataSource,"pic_key",dataSource.getQuery("RETRIEVE_PIC"),user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public byte[] retrievePicData(String user) {
        String path = retrievePicKey(user);
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
    public String retrieveClipKey(String user, String name) {
        return Database.retrieve(dataSource,"clip_key",dataSource.getQuery("RETRIEVE_CLIP"),user,name);
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
    public void deleteClip(String user, String name) {
        //Delete from S3 Bucket
        s3Service.deleteFile(retrieveClipKey(user,name));
        //Delete from database
        Database.query(dataSource,dataSource.getQuery("DELETE_CLIP"),user,name);
    }

}
