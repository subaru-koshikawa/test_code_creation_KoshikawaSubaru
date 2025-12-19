package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * 結合テスト ログイン機能①
 * ケース01
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース01 ログイン画面への遷移")
public class Case01 {


	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
	    // 1. ブラウザを起動し、ログイン画面（トップページ）にアクセスする
	    // WebDriverUtilsのgoToメソッドを使用
	    goTo("http://localhost:8080/lms");

	    // 2. ログイン画面が表示された状態のスクリーンショットを取得する
	    // WebDriverUtilsのgetEvidenceメソッドを使用
	    // これにより "evidence/Case01_test01.png" という名前で保存されます
	    //getEvidence(new Object(){}.getClass());
	    getEvidence(new Object() {});

	    // 3. 画面が正しく表示されているか検証（アサーション）
	    String actualTitle = webDriver.getTitle();
	    org.junit.jupiter.api.Assertions.assertEquals("ログイン | LMS", actualTitle, "ログイン画面が表示されていること");
	}
}
