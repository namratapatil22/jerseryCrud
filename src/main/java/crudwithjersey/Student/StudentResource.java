package crudwithjersey.Student;

import java.util.Arrays;
import java.util.List;

import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("students")
public class StudentResource {
	
	StudentRepository repo=new StudentRepository();
	
	
	//select all student
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Student> getStudents()
	{
		System.out.println("called...");
		
		return repo.getStudents();
	}
	
	//get student by id
	
	@GET
	@Path("student/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student getStudent(@PathParam("id") int id)
	{
		return repo.getStudent(id);
	}
	
	
	//create student
	@POST
	@Path("student")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student createStudent(Student s1)
	{
		System.out.println(s1);
		repo.create(s1);
		return s1;
	}
	
	//update student

	@PUT
	@Path("student")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student updateStudent(Student s1)
	{
		
		System.out.println(s1);
		if(repo.getStudent(s1.getId()).getId()==0)
		{
			repo.create(s1);
			
		}
		else
		{
			
		}
		
		
		
		repo.update(s1);
		return s1;
	}
		
	//delete student
	@DELETE
	@Path("student/{id}")
	public Student deleteStudent(@PathParam("id") int id) 
	{
		Student s=repo.getStudent(id);
		if(s.getId()!=0)
		repo.delete(id);
		return s;
		
	}
	
}
