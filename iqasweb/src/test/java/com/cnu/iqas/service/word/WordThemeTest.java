package com.cnu.iqas.service.word;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.iword.Iword;
import com.cnu.iqas.bean.iword.WordTheme;
import com.cnu.iqas.bean.iword.WordThemeTypeEnum;
import com.cnu.iqas.bean.iword.WordThemeWordRel;
import com.cnu.iqas.service.iword.IwordService;
import com.cnu.iqas.service.iword.WordThemeService;
import com.cnu.iqas.service.iword.WordThemeWordRelService;

/**
* @author 周亮 
* @version 创建时间：2015年12月7日 下午8:12:36
* 类说明
*/
@ContextConfiguration(locations = {"/applicationContext.xml"})//启动Spring容器
public class WordThemeTest extends AbstractTestNGSpringContextTests{//基于TestNG的spring测试框架
	@Autowired  //注入Spring容器中的Bean  ,如果使用@Resource也可以
	private  WordThemeService wordThemeService;
	@Resource
	private WordThemeWordRelService WordThemeWordRelService;
	@Autowired
	private IwordService iwordService;

	//保存“太空”和“草原”两个主题
	@Test(enabled = false)
	public void saveWordTheme(){
		WordTheme th = new WordTheme(WordThemeTypeEnum.SPACE.ordinal());//402880ed517c717701517c7179460000
		wordThemeService.save(th);
		WordTheme th2 = new WordTheme(WordThemeTypeEnum.PRAIRIE.ordinal());//402880ed517c8cf501517c8cf80a0001
		wordThemeService.save(th2);
	}
	//为“太空主题”添加10个单词
	@Test(enabled = false)
	public void saveWordThemeRel(){
		
		QueryResult<Iword> qr = iwordService.getScrollData(1, 10);
		WordTheme theme= wordThemeService.findByType(WordThemeTypeEnum.SPACE.ordinal());
		for( Iword word : qr.getResultlist()){
			WordThemeWordRel entity = new WordThemeWordRel(word.getUuid(), theme.getId());
			WordThemeWordRelService.save(entity);
		}
	}
	//通过“太空”主题查询该主题下的所有单词
	@Test(enabled = true)
	public void getWordsByTheme(){
		WordTheme theme= wordThemeService.findByType(WordThemeTypeEnum.SPACE.ordinal());
		QueryResult<Iword> qr= wordThemeService.getAllWords(theme.getId());
		for( Iword word : qr.getResultlist()){
			System.out.println(word.getContent()+":"+word.getId());
		}
	}
}