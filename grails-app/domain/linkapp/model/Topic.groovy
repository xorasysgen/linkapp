package linkapp.model

import linkapp.Visibility
import linkapp.auth.User

class Topic {

    User createdBy
    String topicName
    Visibility visibility

    static belongsTo = [User]

    static hasMany = [subscriptions:Subscription, resources:Resource]

    static mapping = {
        topicName(blank: false, unique: ['createdBy'])
    }

    static constraints = {
    }


}
