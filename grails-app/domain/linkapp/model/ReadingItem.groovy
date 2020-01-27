package linkapp.model

import linkapp.auth.User

class ReadingItem {

    Date dateCreated
    Date lastUpdated
    Boolean isRead
    static belongsTo = [user: User, resource:Resource]

    static constraints = {
    }
}
