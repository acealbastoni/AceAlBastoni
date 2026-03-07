package model;

public class Job {
    private String plainText;
    private String hashedDescription;
    private String encryptedDescription;
    private String attachedEmails;
    private String profileId;
    private String source;
    private String scrappedDate;
    private String scrappedVersion;
    private String timeInputTheSystem;
    private String sqlGeneratedMD5;

    public String getPlainText() { return plainText; }
    public void setPlainText(String plainText) { this.plainText = plainText; }

    public String getHashedDescription() { return hashedDescription; }
    public void setHashedDescription(String hashedDescription) { this.hashedDescription = hashedDescription; }

    public String getEncryptedDescription() { return encryptedDescription; }
    public void setEncryptedDescription(String encryptedDescription) { this.encryptedDescription = encryptedDescription; }

    public String getAttachedEmails() { return attachedEmails; }
    public void setAttachedEmails(String attachedEmails) { this.attachedEmails = attachedEmails; }

    public String getProfileId() { return profileId; }
    public void setProfileId(String profileId) { this.profileId = profileId; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getScrappedDate() { return scrappedDate; }
    public void setScrappedDate(String scrappedDate) { this.scrappedDate = scrappedDate; }

    public String getScrappedVersion() { return scrappedVersion; }
    public void setScrappedVersion(String scrappedVersion) { this.scrappedVersion = scrappedVersion; }

    public String getTimeInputTheSystem() { return timeInputTheSystem; }
    public void setTimeInputTheSystem(String timeInputTheSystem) { this.timeInputTheSystem = timeInputTheSystem; }

    public String getSqlGeneratedMD5() { return sqlGeneratedMD5; }
    public void setSqlGeneratedMD5(String sqlGeneratedMD5) { this.sqlGeneratedMD5 = sqlGeneratedMD5; }
}
