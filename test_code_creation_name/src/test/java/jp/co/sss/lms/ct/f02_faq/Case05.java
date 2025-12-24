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
 * 結合テスト よくある質問機能 ケース05
 *
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース05 キーワード検索 正常系")
public class Case05 {

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
		webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

		goTo("http://localhost:8080/lms");

		String actualTitle = webDriver.getTitle();
		assertEquals("ログイン | LMS", actualTitle, "ログイン画面が表示されていること");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		WebElement idInput = webDriver.findElement(By.name("loginId"));
		WebElement passInput = webDriver.findElement(By.name("password"));
		WebElement loginBtn = webDriver.findElement(By.cssSelector("input[value='ログイン']"));

		idInput.sendKeys("StudentAA01");
		passInput.sendKeys("StudentBB01");
		loginBtn.click();

		String actualTitle = webDriver.getTitle();
		assertEquals("コース詳細 | LMS", actualTitle, "コース詳細画面が表示されていること");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		WebElement menuToggle = webDriver.findElement(By.className("dropdown-toggle"));

		menuToggle.click();

		WebElement helpLink = webDriver.findElement(By.linkText("ヘルプ"));

		helpLink.click();

		String actualTitle = webDriver.getTitle();
		assertEquals("ヘルプ | LMS", actualTitle, "ヘルプ画面が表示されていること");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// TODO ここに追加
		WebElement faqLink = webDriver.findElement(By.linkText("よくある質問"));

		faqLink.click();

		java.util.List<String> handles = new java.util.ArrayList<>(webDriver.getWindowHandles());
		webDriver.switchTo().window(handles.get(1));

		String actualTitle = webDriver.getTitle();
		assertEquals("よくある質問 | LMS", actualTitle, "よくある質問画面が表示されていること");

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 キーワード検索で該当キーワードを含む検索結果だけ表示")
	void test05() {
		// TODO ここに追加;
		WebElement keywordInput = webDriver.findElement(By.name("keyword"));
		WebElement searchBtn = webDriver.findElement(By.cssSelector("input[value='検索']"));


		keywordInput.sendKeys("助成金");
		searchBtn.click();

		String KeywordResult = webDriver.findElement(By.name("keyword")).getAttribute("value");
		assertTrue(KeywordResult.contains("助成金"), "検索結果にキーワード '助成金' が含まれていません");

		java.util.List<WebElement> resultList = webDriver.findElements(By.tagName("td"));

		assertFalse(resultList.isEmpty(), "検索結果が0件です。キーワードに該当するデータが存在しません。");

		for (WebElement result : resultList) {
			String text = result.getText();
			if (!text.isEmpty()) {
				assertTrue(text.contains("助成金"), "キーワード '助成金' を含まない不適切な結果が表示されています。取得テキスト: " + text);
			}
		}

		scrollTo("document.body.scrollHeight");


		getEvidence(new Object() {

		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 「クリア」ボタン押下で入力したキーワードを消去")
	void test06() {
		// TODO ここに追加
		scrollTo("0");

		WebElement clearBtn = webDriver.findElement(By.cssSelector("input[value='クリア']"));

		clearBtn.click();

		String KeywordResult = webDriver.findElement(By.name("keyword")).getAttribute("value");
		assertTrue(KeywordResult.contains(""), "検索結果にキーワード が含まれていません");

		getEvidence(new Object() {
		});
	}

}
