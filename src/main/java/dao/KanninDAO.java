package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Kannin;
import dto.KanninEntity;
import util.GenerateHashedPw;
import util.GenerateSalt;

public class KanninDAO {
	
	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	public static int registerKannin(Kannin kannin) {
		String sql = "INSERT INTO kannin VALUES(default, ?, ?, ?, ?, ?, ?, ?, current_timestamp)";
		int result = 0;
		

		String salt = GenerateSalt.getSalt(32);
		
		// 取得したソルトを使って平文PWをハッシュ
		String hashedPw = GenerateHashedPw.getSafetyPassword(kannin.getPassword(), salt);
		
		System.out.println("登録時のソルト:" + salt);
		System.out.println("登録時のハッシュPW:" + hashedPw);
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1,kannin.getName());
			pstmt.setInt(2, kannin.getAge());
			pstmt.setString(3,kannin.getGender());
			pstmt.setString(4,kannin.getTel());
			pstmt.setString(5, kannin.getMail());
			pstmt.setString(6, salt);
			pstmt.setString(7, hashedPw);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件更新しました。");
		}
		return result;
	}
		public static List<KanninEntity> selectAllKannin(){
			
			String sql = "SELECT * FROM kannin";
			
			List<KanninEntity> result = new ArrayList<>();
					
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				
				try (ResultSet rs = pstmt.executeQuery()){
					
					while(rs.next()) {

						int id = rs.getInt("id");
						String name = rs.getString("name");
						int anser = rs.getInt("age");
						String gender = rs.getString("gender");
						String tel  = rs.getString("tel");
						String mail = rs.getString("mail");

						KanninEntity kannin = new KanninEntity(id, name, anser, gender, tel, mail);
						
						result.add(kannin);
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public static int deleteKannin(Kannin kannin) {
			String sql = "DELETE FROM kannin WHERE mail =?";
			int result = 0;
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, kannin.getMail());
	

				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} finally {
				System.out.println(result + "件削除しました。");
			}
			return result;
		}
		
		public static String getSalt(String mail) {
			String sql = "SELECT salt FROM kannin WHERE mail = ?";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, mail);

				try (ResultSet rs = pstmt.executeQuery()){
					
					if(rs.next()) {
						String salt = rs.getString("salt");
						return salt;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
	
		public static Kannin login(String mail, String hashedPw) {
			String sql = "SELECT * FROM kannin WHERE mail = ? AND password = ?";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, mail);
				pstmt.setString(2, hashedPw);

				try (ResultSet rs = pstmt.executeQuery()){
					
					if(rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						int age = rs.getInt("age");
						String gender = rs.getString("gender");
						String tel  = rs.getString("tel");
						String email = rs.getString("mail");
						String salt = rs.getString("salt");
						return new Kannin(id, name, age, gender, tel, email, salt, null, null);
						}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
}
