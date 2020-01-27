package linkapp.model

import linkapp.Seriousness
import linkapp.auth.User

class Subscription {

    Seriousness seriousness
    Date dateCreated
    static belongsTo = [subscriber: User, topic:Topic]

    static constraints = {
    }
}
