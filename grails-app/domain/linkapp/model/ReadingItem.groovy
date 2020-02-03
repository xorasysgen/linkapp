package linkapp.model

import linkapp.auth.User

class ReadingItem {

    Date dateCreated
    Date lastUpdated
    Boolean isRead
    Resource resource
    User user

    static belongsTo = [user: User, resource:Resource]

    static constraints = {
    }

    String toString() {
        return "${user} read the ${resource}"
    }
}
