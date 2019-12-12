package com.springboot.vue;

import com.springboot.vue.pojo.Users;
import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TestApplication  {

	@Autowired
	private KieSession kieSession;
	@Test
	public void testHelloWord() {
		System.out.println("测试。。。。。");
		Users user=new Users(18,"张三");
		kieSession.insert(user);
		kieSession.fireAllRules();
		System.err.println("规则执行完毕后张三变为了："+user.getUsername());
		kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("user"));

	}
}
