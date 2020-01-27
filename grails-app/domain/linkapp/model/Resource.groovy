package linkapp.model

import linkapp.auth.User

class Resource {

    User createdBy
    String description
    Date dateCreated
    Date lastModified

    static hasMany = [readingItems: ReadingItem]
    static belongsTo = [topic: Topic]

    static constraints = {
        description nullable: false, blank: false, maxSize: 1024
    }
}
