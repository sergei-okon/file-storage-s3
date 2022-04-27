package ua.com.sergeiokon.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class S3BucketService {

    private final S3Connector s3Connector;

    public S3BucketService(ua.com.sergeiokon.s3.S3Connector s3Connector) {
        this.s3Connector = s3Connector;
    }


    public void createBucket() {
        AmazonS3 s3client = s3Connector.getS3client();
        String bucketName = "baeldung-bucket";
        if (s3client.doesBucketExist(bucketName)) {
            log.info("Bucket name is not available."
                    + " Try again with a different Bucket name.");
            return;
        }
        s3client.createBucket(bucketName);
    }

    public List<Bucket> getBucket() {
        AmazonS3 s3client = s3Connector.getS3client();
        List<Bucket> buckets = s3client.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }
        return buckets;
    }

    public void deleteBucket(String bucketName) {
        AmazonS3 s3client = s3Connector.getS3client();
        try {
            s3client.deleteBucket("baeldung-bucket-test2");
        } catch (
                AmazonServiceException e) {
            System.err.println("e.getErrorMessage()");
            return;
        }
    }
}
