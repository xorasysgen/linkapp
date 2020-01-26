package linkapp

class SystemInfo {

    Integer id;
    Date dateCreated;
    Date lastModified;

    static constraints = {
        table : 'system_information'
        dateCreated type : 'timestamp'
        lastModified type : 'timestamp'
    }
}
