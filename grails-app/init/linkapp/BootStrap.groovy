package linkapp

import linkapp.auth.*

class BootStrap {

    def init = { servletContext ->


        def adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')
        def userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')

     /*   def admin = User.findOrSaveWhere(username: 'root', password: 'root', firstName: 'anurag', lastName: 'bhaskar', email: 'abanurag@gmail.com',
        )

        def user = User.findOrSaveWhere(username: 'sushil', password: 'sushil', firstName: 'sushil', lastName: 'kumar', email: 'sushil.bhaskar@gmail.com',
        )



        if (!admin.authorities.contains(adminRole)) {
            UserRole.create(admin, adminRole, true)
        }

        if (!user.authorities.contains(adminRole)) {
            UserRole.create(user, userRole, true)
        }

        UserRole.withSession {
            it.flush()
            it.clear()
        }*/

    }
    def destroy = {
    }
}
