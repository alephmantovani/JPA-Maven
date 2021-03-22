package com.DAO;

import com.Modelos.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author S430
 */
public class ConexaoDAO {

    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        return emf.createEntityManager();
    }

//--------------------------------------SALVAR ou ALTERAR
    public Cliente salvar(Cliente cli) throws Exception {
        EntityManager em = getEM();

        try {
            em.getTransaction().begin();

            if (cli.getId() == null) {
                em.persist(cli); //Insert
                JOptionPane.showMessageDialog(null, "Cadastro inserido com sucesso", "AVISO", 1);
            } else {
                //Verifica se o cadastro está disponível no BD
                if (!em.contains(cli)) {
                    if (em.find(Cliente.class, cli.getId()) == null) {
                        throw new Exception("Erro ao tentar atualizar!");
                    }
                }
                cli = em.merge(cli); //Update
                JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso", "AVISO", 1);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return cli;
    }

//--------------------------------------REMOVER
    public void remover(Long id) throws Exception {
        EntityManager em = getEM();
        Cliente cli = em.find(Cliente.class, id);

        try {
            em.getTransaction().begin();
            em.remove(cli);//Remove
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Cadastro removido com sucesso", "AVISO", 1);
        } catch (Exception e) {
            throw new Exception("Erro ao fazer a consulta");
        } finally {
            em.close();
        }
    }
    
//--------------------------------------CONSULTAR POR ID
    public Cliente consulta(Long id) throws Exception{
        EntityManager em = getEM();
        Cliente cli = null;
        
        try {
            cli = em.find(Cliente.class, id); //Select
        } catch (Exception e) {
            throw new Exception("Erro ao fazer a consulta");
        } finally{
            em.close();
        }
        
        return cli;
    }
    
//--------------------------------------CONSULTAR TUDO 
    public List<Cliente> consultaTudo(){
        EntityManager em = getEM();
        List<Cliente> list = null; 
        
        try {
            
            list = em.createQuery("from Cliente c").getResultList();
            
        } catch (Exception e) {
            System.err.println("Erro" + e);
        }finally{
            em.close();
        }
        
        return list;
    }
}
