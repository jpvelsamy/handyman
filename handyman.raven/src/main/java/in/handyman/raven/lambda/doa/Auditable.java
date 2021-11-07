package in.handyman.raven.lambda.doa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@MappedSuperclass
public abstract class Auditable {
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;
    @Column(name = "created_by")
    private Long createdBy = -1L;
    @Column(name = "last_modified_by")
    private Long lastModifiedBy = -1L;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(final LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(final Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
