package PresentTier;

import PresentTier.animals.MUAnimalPage;
import PresentTier.animals.UUAnimalPage;
import PresentTier.health.MHPage;
import PresentTier.health.UHPage;
import PresentTier.shelters.MSPage;
import PresentTier.shelters.USPage;
import PresentTier.user.MUserPage;
import PresentTier.user.UUserPage;
import PresentTier.vaccinesImfo.MVPage;
import PresentTier.vaccinesImfo.UVPage;

public class MainPage{
	public LoginPage loginpage;		//登录界面
	public ChoosePage choosePage;	//功能选择界面
	//用户界面
	public UUserPage UUP;			//用户查询界面
	public UUAnimalPage UAP;		//动物查询界面
	public USPage USP;				//收容所查询界面
	public MHPage MHP;				//健康信息查询界面
	public MVPage MVP;				//疫苗信息查询界面

	//管理员界面
	public MUserPage MUP;			//用户查询界面
	public MUAnimalPage MAP;		//动物查询界面
	public MSPage MSP;				//收容所查询界面
	public UHPage UHP;				//健康查询界面
	public UVPage UVP;				//疫苗信息查询界面
	
	public MainPage() {
		// TODO Auto-generated constructor stub
		loginpage = new LoginPage();
		choosePage = new ChoosePage();
		
		UUP = new UUserPage();
		MUP = new MUserPage();
		UAP = new UUAnimalPage();
		MAP = new MUAnimalPage();
		USP = new USPage();
		MSP = new MSPage();
		MHP = new MHPage();
		UHP = new UHPage();
		MVP = new MVPage();
		UVP = new UVPage();
		
		loginpage.loginPage.setVisible(true);
	}
}
