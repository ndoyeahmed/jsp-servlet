package dao;

import config.HibernateInitializerConfig;
import model.RendezVous;
import model.Role;
import model.Utilisateur;
import org.hibernate.Session;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mouhamed NDOYE
 * @since 2019-06-01
 * @version 1.0.0
 *
 * Class that implement the IUtilisateurDao interface
 */
@Stateless
public class UtilisateurDao implements IUtilisateurDao {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Session session = HibernateInitializerConfig.getSession();

    @Override
    public boolean create(Utilisateur user) {
        try {
            session.save(user);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Utilisateur user) {
        try {
            session.update(user);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return false;
        }
    }

    @Override
    public List<Utilisateur> all() {
        try {
            return session.createQuery("select u from Utilisateur u", Utilisateur.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Utilisateur getUserById(Integer id) {
        try {
            return session.createQuery("select u from Utilisateur u where u.id=:id", Utilisateur.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return null;
        }
    }

    @Override
    public Utilisateur getUserByUsername(String username) {
        try {
            Utilisateur utilisateur = session.createQuery("select u from Utilisateur u where u.login=:username", Utilisateur.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return utilisateur != null && utilisateur.getId() != null ? utilisateur : null;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return null;
        }
    }

    @Override
    public Utilisateur getUserByUsernameAndPassword(String login, String password) {
        try {
            Utilisateur utilisateur =  session.createQuery("select u from Utilisateur u where " +
                    "u.login=:login and u.password=:password", Utilisateur.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
            return utilisateur != null && utilisateur.getId() != null ? utilisateur : null;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return null;
        }
    }

    @Override
    public boolean createRole(Role role) {
        try {
            session.save(role);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateRole(Role role) {
        try {
            session.update(role);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return false;
        }
    }

    @Override
    public List<Role> allRoles() {
        try {
            return session.createQuery("select r from Role r", Role.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Role getRoleById(Integer id) {
        try {
            return session.createQuery("select r from Role r where r.id=:id", Role.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return null;
        }
    }

    @Override
    public boolean createRV(RendezVous rendezVous) {
        try {
            session.save(rendezVous);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateRV(RendezVous rendezVous) {
        try {
            session.update(rendezVous);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return false;
        }
    }

    @Override
    public List<RendezVous> allRV() {
        try {
            return session.createQuery("select r from RendezVous r", RendezVous.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return null;
        }
    }

    @Override
    public RendezVous getRVByID(Integer id) {
        try {
            return session.createQuery("select r from RendezVous r where r.id=:id", RendezVous.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return null;
        }
    }
}
