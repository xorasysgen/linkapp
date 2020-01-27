package linkapp.model

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class DocumentResourceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DocumentResource.list(params), model:[documentResourceCount: DocumentResource.count()]
    }

    def show(DocumentResource documentResource) {
        respond documentResource
    }

    def create() {
        respond new DocumentResource(params)
    }

    @Transactional
    def save(DocumentResource documentResource) {
        if (documentResource == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (documentResource.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond documentResource.errors, view:'create'
            return
        }

        documentResource.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), documentResource.id])
                redirect documentResource
            }
            '*' { respond documentResource, [status: CREATED] }
        }
    }

    def edit(DocumentResource documentResource) {
        respond documentResource
    }

    @Transactional
    def update(DocumentResource documentResource) {
        if (documentResource == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (documentResource.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond documentResource.errors, view:'edit'
            return
        }

        documentResource.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), documentResource.id])
                redirect documentResource
            }
            '*'{ respond documentResource, [status: OK] }
        }
    }

    @Transactional
    def delete(DocumentResource documentResource) {

        if (documentResource == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        documentResource.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), documentResource.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
