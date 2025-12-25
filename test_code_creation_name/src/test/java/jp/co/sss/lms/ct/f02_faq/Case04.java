package jp.co.sss.lms.ct.f02_faq;

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
import org.openqa.selenium.WebElement;

/**
 * 結合テスト よくある質問機能 ケース04
 *
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

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
		// 待機時間の設定
		webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

		// 遷移
		goTo("http://localhost:8080/lms");

		// 検証
		String actualTitle = webDriver.getTitle();
		assertEquals("ログイン | LMS", actualTitle, "ログイン画面が表示されていること");

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// 要素取得
		WebElement idInput = webDriver.findElement(By.name("loginId"));
		WebElement passInput = webDriver.findElement(By.name("password"));
		WebElement loginBtn = webDriver.findElement(By.cssSelector("input[value='ログイン']"));

		// 操作処理
		idInput.sendKeys("StudentAA01");
		passInput.sendKeys("StudentBB01");
		loginBtn.click();

		// 検証
		String actualTitle = webDriver.getTitle();
		assertEquals("コース詳細 | LMS", actualTitle, "コース詳細画面が表示されていること");

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// 要素取得
		WebElement menuToggle = webDriver.findElement(By.className("dropdown-toggle"));

		// 操作処理
		menuToggle.click();

		// 要素取得
		WebElement helpLink = webDriver.findElement(By.linkText("ヘルプ"));

		// 操作処理
		helpLink.click();

		// 検証
		String actualTitle = webDriver.getTitle();
		assertEquals("ヘルプ | LMS", actualTitle, "ヘルプ画面が表示されていること");

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// 要素取得
		WebElement faqLink = webDriver.findElement(By.linkText("よくある質問"));

		// 操作処理
		faqLink.click();

		// 別タブへの遷移
		java.util.List<String> handles = new java.util.ArrayList<>(webDriver.getWindowHandles());
		webDriver.switchTo().window(handles.get(1));

		// 検証
		String actualTitle = webDriver.getTitle();
		assertEquals("よくある質問 | LMS", actualTitle, "よくある質問画面が表示されていること");

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

}
