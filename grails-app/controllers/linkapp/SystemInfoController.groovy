package linkapp

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemInfoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SystemInfo.list(params), model:[systemInfoCount: SystemInfo.count()]
    }

    def show(SystemInfo systemInfo) {
        respond systemInfo
    }

    def create() {
        respond new SystemInfo(params)
    }

    @Transactional
    def save(SystemInfo systemInfo) {
        if (systemInfo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemInfo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemInfo.errors, view:'create'
            return
        }

        systemInfo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemInfo.label', default: 'SystemInfo'), systemInfo.id])
                redirect systemInfo
            }
            '*' { respond systemInfo, [status: CREATED] }
        }
    }

    def edit(SystemInfo systemInfo) {
        respond systemInfo
    }

    @Transactional
    def update(SystemInfo systemInfo) {
        if (systemInfo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemInfo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemInfo.errors, view:'edit'
            return
        }

        systemInfo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemInfo.label', default: 'SystemInfo'), systemInfo.id])
                redirect systemInfo
            }
            '*'{ respond systemInfo, [status: OK] }
        }
    }

    @Transactional
    def delete(SystemInfo systemInfo) {

        if (systemInfo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        systemInfo.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemInfo.label', default: 'SystemInfo'), systemInfo.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemInfo.label', default: 'SystemInfo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
