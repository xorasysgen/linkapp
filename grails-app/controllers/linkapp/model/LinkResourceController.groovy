package linkapp.model

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class LinkResourceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond LinkResource.list(params), model:[linkResourceCount: LinkResource.count()]
    }

    def show(LinkResource linkResource) {
        respond linkResource
    }

    def create() {
        respond new LinkResource(params)
    }

    @Transactional
    def save(LinkResource linkResource) {
        if (linkResource == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (linkResource.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond linkResource.errors, view:'create'
            return
        }

        linkResource.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), linkResource.id])
                redirect linkResource
            }
            '*' { respond linkResource, [status: CREATED] }
        }
    }

    def edit(LinkResource linkResource) {
        respond linkResource
    }

    @Transactional
    def update(LinkResource linkResource) {
        if (linkResource == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (linkResource.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond linkResource.errors, view:'edit'
            return
        }

        linkResource.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), linkResource.id])
                redirect linkResource
            }
            '*'{ respond linkResource, [status: OK] }
        }
    }

    @Transactional
    def delete(LinkResource linkResource) {

        if (linkResource == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        linkResource.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), linkResource.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
