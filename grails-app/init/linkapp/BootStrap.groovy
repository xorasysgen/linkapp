package linkapp

import linkapp.auth.*

class BootStrap {

    def init = { servletContext ->


        def adminRole = new Role(authority: 'ROLE_ADMIN');
        adminRole.save()
        def userRole = new Role(authority: 'ROLE_USER');
        userRole.save()

      def admin = new User(username: 'root', password: 'root', firstName: 'anurag', lastName: 'bhaskar', email: 'abanurag@gmail.com',
        )
        admin.save()

        def user = new User(username: 'sushil', password: 'sushil', firstName: 'sushil', lastName: 'kumar', email: 'sushil.bhaskar@gmail.com',
        )

        user.save()

            UserRole.create(admin, adminRole, true)

            UserRole.create(user, userRole, true)

        UserRole.withSession {
            it.flush()
            it.clear()
        }

    }
    def destroy = {
    }
}
