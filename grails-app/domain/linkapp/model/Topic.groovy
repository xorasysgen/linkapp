package linkapp.model

import linkapp.auth.User

class Topic {

    String name

    static belongsTo = [owner: User]

    static hasMany = [subscriptions:Subscription, resources:Resource]

    static mapping = {

    }

    static constraints = {
    }

    @Override
    String toString() {
        return name
    }
}
