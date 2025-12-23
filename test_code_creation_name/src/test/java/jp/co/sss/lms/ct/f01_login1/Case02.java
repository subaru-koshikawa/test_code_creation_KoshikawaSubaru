package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

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
		// 待機時間の設定（Implicit Wait）
		webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

		// 遷移
		goTo("http://localhost:8080/lms");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 存在しないユーザーでログイン失敗")
	void test02() {

		// 入力・クリック
		webDriver.findElement(By.name("loginId")).sendKeys("999999");
		webDriver.findElement(By.name("password")).sendKeys("password");
		webDriver.findElement(By.cssSelector("input[value='ログイン']")).click();

		// メッセージ取得
		String errorMessage = webDriver.findElement(By.xpath("//span[contains(text(), 'ログインに失敗しました。')]")).getText();

		// 検証
		assertEquals("* ログインに失敗しました。", errorMessage);

		// エビデンス取得
		getEvidence(new Object() {
		});
	}
}
