package ua.com.sergeiokon.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class S3Connector {

    @Value("${aws.access}")
    private String accessKey;

    @Value("${aws.secret}")
    private String secretKey;

    private AWSCredentials credentials;

    @PostConstruct
    private void postConstruct() {
        credentials = new BasicAWSCredentials(accessKey, secretKey);
    }

    public AmazonS3 getS3client() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_WEST_3)
                .build();
    }
}
