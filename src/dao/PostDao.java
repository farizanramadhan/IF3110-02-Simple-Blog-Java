package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Post;
import util.DbUtil;

public class PostDao {
	private Connection connection;

    public void addPost(Post post){
    	Connection conn = null;
    	try{
    		conn = DbUtil.getConnection();
    		PreparedStatement pStmt = conn.prepareStatement(
    				"insert into posts(title,content,date,status) values(?,?,?,?)"
    				);
    		pStmt.setString(1, post.getTitle());
    		pStmt.setString(2, post.getContent());
    		pStmt.setDate(3, new java.sql.Date(post.getDate().getTime()));
    		pStmt.setString(4, post.getStatus());
    		pStmt.executeUpdate();
    	} catch(Exception e){
    		e.printStackTrace();
    	} finally{
    		DbUtil.closeConnection(conn);
    	}
    }
    
    public void deletePost(int postId){
    	Connection conn = null;
    	try{
    		conn = DbUtil.getConnection();
    		PreparedStatement pStmt = conn.prepareStatement(
    				"delete from posts where id=?"
    				);
    		pStmt.setInt(1,postId);
    		pStmt.executeUpdate();
    	} catch(SQLException e){
    		e.printStackTrace();
    	} finally{
    		DbUtil.closeConnection(conn);
    	}
    }
    
    public void updatePost(Post post){
    	Connection conn = null;
    	try{
    		conn = DbUtil.getConnection();
    		PreparedStatement pStmt = conn.prepareStatement(
    				"update posts set title=?, content=?, date=?, status=?"+"where id=?"
    				);
    		pStmt.setString(1, post.getTitle());
    		pStmt.setString(2, post.getContent());
    		pStmt.setDate(3, new java.sql.Date(post.getDate().getTime()));
    		pStmt.setString(4, post.getStatus());
    		pStmt.executeUpdate();
    	} catch(SQLException e){
    		e.printStackTrace();
    	} finally{
    		DbUtil.closeConnection(conn);
    	}
    }
    
    public List<Post> getAllPosts(){
    	Connection conn = null;
    	List<Post> posts = new ArrayList<Post>();
    	try{
    		conn = DbUtil.getConnection();
    		Statement stmt = conn.createStatement();
    		ResultSet rSet = stmt.executeQuery("select * from posts");
    		while(rSet.next()){
    			Post post = new Post();
    			post.setId(rSet.getInt("id"));
    			post.setTitle(rSet.getString("title"));
    			post.setContent(rSet.getString("content"));
    			post.setDate(rSet.getDate("date"));
    			post.setStatus(rSet.getString("status"));
    			posts.add(post);
    		}
    	} catch(SQLException e){
    		e.printStackTrace();
    	} finally{
    		DbUtil.closeConnection(conn);
    	}
    	
    	return posts;
    }
    
    public Post getPostById(int postid){
    	Connection conn = null;
    	Post post = new Post();
    	try{
    		conn = DbUtil.getConnection();
    		PreparedStatement pStmt = conn.prepareStatement(
    				"select * from posts where id=?"
    				);
    		pStmt.setInt(1, postid);
    		ResultSet rSet = pStmt.executeQuery();
    		
    		if(rSet.next()){
    			post.setId(rSet.getInt("id"));
    			post.setTitle(rSet.getString("title"));
    			post.setContent(rSet.getString("content"));
    			post.setDate(rSet.getDate("date"));
    			post.setStatus(rSet.getString("status"));
    		}
    	} catch(SQLException e){
    		e.printStackTrace();
    	} finally{
    		DbUtil.closeConnection(conn);
    	}
    	
    	return post;
    }
}
