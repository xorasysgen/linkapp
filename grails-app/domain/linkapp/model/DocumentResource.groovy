package linkapp.model

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class DocumentResource extends Resource {
    String fileName
    String contentType

    static mapping = {
    }

    static constraints = {
        fileName blank: false, nullable: false
        contentType blank: true, nullable: true
    }

    @Override
    public String toString() {
        return "${fileName}";
    }
}