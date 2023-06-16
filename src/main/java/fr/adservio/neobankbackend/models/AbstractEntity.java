package fr.adservio.neobankbackend.models;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "creation_date", updatable = false, length = 12)
    private Instant createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "last_update_date", nullable = true, length = 12)
    private Instant lastUpdateDate;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delete_date", nullable = true, length = 12)
    private Instant deleteDate;

    @Column(name = "deleted", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean deleted;

    @Column(name = "active", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", nullable = true, referencedColumnName = "id")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "updated_by", nullable = true, referencedColumnName = "id")
    private User updatedBy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "deleted_by", nullable = true, referencedColumnName = "id")
    private User deletedBy;

}
