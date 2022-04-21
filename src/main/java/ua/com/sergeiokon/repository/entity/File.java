package ua.com.sergeiokon.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", nullable = false, length = 100)
    private String fileName;

    @Column(name = "location", nullable = false, length = 1020)
    private String location;

    @Column(name = "bucket")
    private String bucket;

    public File(String originalFilename) {
    }
}
