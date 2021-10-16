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
	public LoginPage loginpage;		//��¼����
	public ChoosePage choosePage;	//����ѡ�����
	//�û�����
	public UUserPage UUP;			//�û���ѯ����
	public UUAnimalPage UAP;		//�����ѯ����
	public USPage USP;				//��������ѯ����
	public MHPage MHP;				//������Ϣ��ѯ����
	public MVPage MVP;				//������Ϣ��ѯ����

	//����Ա����
	public MUserPage MUP;			//�û���ѯ����
	public MUAnimalPage MAP;		//�����ѯ����
	public MSPage MSP;				//��������ѯ����
	public UHPage UHP;				//������ѯ����
	public UVPage UVP;				//������Ϣ��ѯ����
	
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
