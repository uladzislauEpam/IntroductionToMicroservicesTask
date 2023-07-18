package com.epam.uladzislau.resource.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resource")
public class Resource {

    public Resource() {}

    public Resource(byte[] audioBytes) {
        this.audioBytes = audioBytes;
    }

    public Resource(Long id, byte[] audioBytes, Long metadataId) {
        this.id = id;
        this.metadataId = metadataId;
        this.audioBytes = audioBytes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "audio_bytes", columnDefinition = "bytea")
    private byte[] audioBytes;

    @Column(name = "metadata_id")
    private Long metadataId;

    public Long getId() {
        return id;
    }

    public Long getMetadataId() {
        return metadataId;
    }

    public byte[] getAudioBytes() {
        return audioBytes;
    }

    public void setMetadataId(Long metadataId) {
        this.metadataId = metadataId;
    }
}
