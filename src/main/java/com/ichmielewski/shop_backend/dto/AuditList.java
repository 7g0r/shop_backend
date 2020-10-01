package com.ichmielewski.shop_backend.dto;
import java.util.List;

public class AuditList<DTO extends AbstractDTO> {
    private List<DTO> modified;
    private List<Long> deleted;
    private Integer lastRevision;

    public List<DTO> getModified() {
        return modified;
    }

    public void setModified(List<DTO> modified) {
        this.modified = modified;
    }

    public List<Long> getDeleted() {
        return deleted;
    }

    public void setDeleted(List<Long> deleted) {
        this.deleted = deleted;
    }

    public Integer getLastRevision() {
        return lastRevision;
    }

    public void setLastRevision(Integer lastRevision) {
        this.lastRevision = lastRevision;
    }
}
