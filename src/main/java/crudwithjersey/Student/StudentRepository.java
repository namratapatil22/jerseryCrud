package crudwithjersey.Student;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class StudentRepository
{
		
	Connection con=null;

	
	public StudentRepository() 
	{
		String jdbcUrl="jdbc:mysql://localhost:3306/studentdb2?useSSL=false";
	    String jdbcUsername="root";
	    String jdbcPassword="Namrata@22";
		//String jdbcDriver="com.mysql.jdbc.Driver";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");				
			con=DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword);
			}
		catch(Exception e)
		{
			System.out.println(e);
		}		
	}
	
	public List<Student> getStudents()
	{
		List<Student> students=new ArrayList<>();
		String sql="select * from student";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			while(rs.next())
			{
				Student s=new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setDob(rs.getString(3));
				s.setDoj(rs.getString(4));
				
				students.add(s);
							
				
				/*int id      =rs.getInt("id");
				 String name =rs.getString("name");					 
				 String dob    =rs.getString("dob");
				 String doj    =rs.getString("doj");
				 students.add(new Student(id,name,dob,doj));*/
				
			}			
		} catch (SQLException e) {
			System.out.println(e);
		}		
		return students;
	}
		
	public Student getStudent(int id)
	{
		String sql="select * from student where id="+id;
		Student s=new Student();
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			if(rs.next())
			{
				
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setDob(rs.getString(3));
				s.setDoj(rs.getString(4));
			}
	}
		catch(Exception e) 
		{
		System.out.println(e);
		}
		return s;

	}	

	public void create(Student s1)
	{
		
		String sql="insert into student values(?,?,?,?)";
	  try
	  {
		PreparedStatement st=con.prepareStatement(sql);
			
		st.setInt(1, s1.getId());
		st.setString(2, s1.getName());
		st.setString(3, s1.getDob());
		st.setString(4, s1.getDoj());
		
		st.executeUpdate();
	}
	catch(Exception e) 
	{
	System.out.println(e);
	}
}
	
	
	public void update(Student s1)
	{
		
		String sql="update student set name=?,dob=?,doj=? where id=?";
	  try
	  {
		PreparedStatement st=con.prepareStatement(sql);
			
		
		st.setString(1, s1.getName());
		st.setString(2, s1.getDob());
		st.setString(3, s1.getDoj());
		st.setInt(4, s1.getId());

		
		st.executeUpdate();
	}
	catch(Exception e) 
	{
	System.out.println(e);
	}
}

	public void delete(int id) 
	{
		String sql="delete from student where id=?";
		  try
		  {
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1,id);

			
			st.executeUpdate();
		}
		catch(Exception e) 
		{
		System.out.println(e);
		}
		
		
	}
	
	
	
	
	
}
