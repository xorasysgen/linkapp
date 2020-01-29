package linkapp.model

import jdk.nashorn.internal.objects.annotations.Getter
import jdk.nashorn.internal.objects.annotations.Setter
import linkapp.Seriousness
import linkapp.auth.User


class Subscription {

    Seriousness seriousness
    Date dateCreated
    Topic topic
    User createdBy
    static belongsTo = [createdBy: User, topic:Topic]

    static constraints = {
    }
   /* String toString() {
        return "${createdBy} subscribed ${topic}"

    }*/
}

