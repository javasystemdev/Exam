package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//StudentList.action が該当する
@WebServlet(urlPatterns = { "*.action" })
public class FrontController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			//パスを取得
			String path = request.getServletPath().substring(1);
			//ファイル名を取得しクラス名に変換
			String name = path.replace(".a", "A").replace('/', '.');

			System.out.println("★ servlet path -> " + request.getServletPath());
			System.out.println("★ class name -> " + name);

			//StudentList.action が StudentListAction に置き換えられる

			//アクションクラスのインスタンスを返却
			Action action = (Action)Class.forName(name).getDeclaredConstructor().newInstance();

			//遷移先URLを取得
			//StudentListAction.javaを実行する
			action.execute(request, response);
			//String url = action.execute(request, response);
			//request.getRequestDispatcher(url).forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
			//エラーページへリダイレクト
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}
