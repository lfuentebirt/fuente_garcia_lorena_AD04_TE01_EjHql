package ejercicioHql;

import entidades.University;

import java.util.List;

import entidades.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ejercicioHql {
	public static void main(String[] args) {
		/* Por último realiza la siguiente consulta sobre tu base de datos en usando HQL: 
		 * Lista el nombre completo de los alumnos junto con el nombre de sus universidades. 
		 * 
		 */
		// creamos el Entity Manager Factory y el entity manager
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory("demodb");
		
		EntityManager manager = factoria.createEntityManager();
		
		// comenzamos la transaccion
		manager.getTransaction().begin();
		try {		
			// creamos una lista de Estudiantes, que a suvez contiene la universidad
			List<Student> estuList = manager.createQuery("from Student").getResultList();
			
			// mostramos por pantalla
			mostrarUniStudent(estuList);
			
		}catch(Exception e) {
			// si hay excepcion realizamos un rollback
			System.out.println("Rollback");
			manager.getTransaction().rollback();
			
			e.printStackTrace();
		}finally {
			manager.close();
			factoria.close();
		}
	}

	private static void mostrarUniStudent(List<Student> estuList) {
		// recorremos las universidades
		for(Student estu: estuList) {
			String datosHql = new String();
			// recorremos los estudiantes de la universidad
			datosHql = "ID: " + estu.getId() + " - Estudiante: " + estu.getFirstName() + " " + estu.getLastName() + " - Univesidad: ";
			// si se ha asignado una universidad (registros de ejercicios anteriores a class Univserity)
			if(estu.getUniversity() != null) {
				datosHql += estu.getUniversity().getName();
			}else {
				datosHql += "No tiene";
			}
			//imprimimos por pantalla
			System.out.println(datosHql);
		}
		
	}
}
