package jp.co.sss.lms.ct.f03_report;

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
import org.openqa.selenium.support.ui.Select;

/**
 * 結合テスト レポート機能 ケース08
 *
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

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

		// 待機時間の設定
				webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

		// 検証
		String actualTitle = webDriver.getTitle();
		assertEquals("コース詳細 | LMS", actualTitle, "コース詳細画面が表示されていること");

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// 要素取得
		WebElement sectionBtn = webDriver.findElement(By.xpath("//form[input[@value='2']]//input[@value='詳細']"));

		// 操作処理(クリック・メッセージ送信）
		sectionBtn.click();

		// 待機時間の設定
		webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

		// 検証
		String actualTitle = webDriver.getTitle();
		assertEquals("セクション詳細 | LMS", actualTitle, "セクション詳細画面が表示されていること");

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// 要素取得
		WebElement sendBtn = webDriver.findElement(By.cssSelector("input[type='submit'][value*='提出済み週報【デモ】を確認する']"));

		// スクロール
		scrollTo("document.body.scrollHeight");

		// 操作処理
		sendBtn.click();

		// 待機時間の設定
		webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

		// 検証
		String actualTitle = webDriver.getTitle();
		assertEquals("レポート登録 | LMS", actualTitle, "レポート登録画面が表示されていること");

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		// 待機時間の設定
		webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
		// 要素取得
		WebElement achievementlevelText = webDriver.findElement(By.id("content_0"));
		WebElement impressionText = webDriver.findElement(By.id("content_1"));
		WebElement weeklyReviewText = webDriver.findElement(By.id("content_2"));
		WebElement levelUnderstanding = webDriver.findElement(By.id("intFieldValue_0"));
		WebElement submitBtn = webDriver.findElement(By.cssSelector("button.btn.btn-primary[type='submit']"));

		Select select = new Select(levelUnderstanding);


		// スクロール
		scrollTo("document.body.scrollHeight");

		// 操作処理
		select.selectByValue("3");
		achievementlevelText.clear();
		achievementlevelText.sendKeys("5");
		impressionText.clear();
		impressionText.sendKeys("週報テスト");
		weeklyReviewText.clear();
		weeklyReviewText.sendKeys("アルゴリズム、フローチャートを理解する事ができました");
		submitBtn.click();

		// 待機時間の設定
		webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(7));

		// 検証
		String actualTitle = webDriver.getTitle();
		assertEquals("セクション詳細 | LMS", actualTitle, "セクション詳細画面が表示されていること");

		// エビデンス取得
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		// 要素取得
		WebElement welcomeLink = webDriver.findElement(By.cssSelector("a[href='/lms/user/detail']"));

		// 操作処理
		welcomeLink.click();

		// 検証
		String actualTitle = webDriver.getTitle();
		assertEquals("ユーザー詳細", actualTitle, "ユーザー詳細画面が表示されていること");

		// エビデンス取得
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		// 要素取得
		WebElement sectionBtn = webDriver.findElement(By.xpath("//form[input[@value='3']]//input[@value='詳細']"));

		// スクロール
		scrollTo("document.body.scrollHeight");

		// 操作処理
		sectionBtn.click();

		// 待機時間の設定
		webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

		// 要素取得
		WebElement achievementLevel = webDriver.findElement(By.xpath("//th[text()='目標の達成度']/following-sibling::td"));
		WebElement select = webDriver.findElement(By.xpath("//td[p[text()='ITリテラシー①']]/following-sibling::td/p"));
		WebElement impression = webDriver.findElement(By.xpath("//th[text()='所感']/following-sibling::td"));
		WebElement weeklyReview = webDriver.findElement(By.xpath("//th[text()='一週間の振り返り']/following-sibling::td"));

		// 実際の値取得
		String actualLevel = achievementLevel.getText();
		String actualSelect = select.getText();
		String actualImpression = impression.getText();
		String actualWeeklyReview = weeklyReview.getText();

		// 検証
		assertEquals("5", actualLevel, "達成度が正しく反映されていること");
		assertEquals("3", actualSelect, "理解度が正しく反映されていること");
		assertEquals("週報テスト", actualImpression, "所感が正しく反映されていること");
		assertEquals("アルゴリズム、フローチャートを理解する事ができました", actualWeeklyReview, "振り返り内容が正しく反映されていること");

		// エビデンス
		getEvidence(new Object() {
		});
	}

}
