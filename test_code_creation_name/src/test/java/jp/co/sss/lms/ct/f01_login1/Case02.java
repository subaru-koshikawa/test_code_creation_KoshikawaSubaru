package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

/**
 * 結合テスト ログイン機能① ケース02
 *
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース02 受講生 ログイン 認証失敗")
public class Case02 {

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
		// TODO ここに追加
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 存在しないユーザーでログイン失敗")
	void test02() {
		// 1. 待機時間の設定（Implicit Wait）
		webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

		// 2. 遷移と入力・クリック
		goTo("http://localhost:8080/lms");
		webDriver.findElement(By.name("loginId")).sendKeys("999999");
		webDriver.findElement(By.name("password")).sendKeys("password");
		webDriver.findElement(By.cssSelector("input[value='ログイン']")).click();

		// 3. メッセージ取得
		String errorMessage = webDriver.findElement(By.xpath("//span[contains(text(), 'ログインに失敗しました。')]")).getText();

		// 4. 検証
		org.junit.jupiter.api.Assertions.assertEquals("* ログインに失敗しました。", errorMessage);

		// 5. エビデンス取得
		getEvidence(new Object() {});
	}
}


