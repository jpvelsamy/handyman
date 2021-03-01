/**
 * generated by Xtext 2.16.0
 */
package in.handyman.dsl.impl;

import in.handyman.dsl.Abort;
import in.handyman.dsl.Action;
import in.handyman.dsl.Callprocess;
import in.handyman.dsl.Catch;
import in.handyman.dsl.ClickSendSms;
import in.handyman.dsl.Copydata;
import in.handyman.dsl.Doozle;
import in.handyman.dsl.Dropfile;
import in.handyman.dsl.DslFactory;
import in.handyman.dsl.DslPackage;
import in.handyman.dsl.ElasticFBCLead;
import in.handyman.dsl.ElasticGET;
import in.handyman.dsl.EsAccumulate;
import in.handyman.dsl.EsUpdate;
import in.handyman.dsl.ExecJava;
import in.handyman.dsl.Expression;
import in.handyman.dsl.FBCLead;
import in.handyman.dsl.FBFormDownload;
import in.handyman.dsl.Fetch;
import in.handyman.dsl.Finally;
import in.handyman.dsl.FirebaseDatabasePut;
import in.handyman.dsl.FirebaseReactiveNotification;
import in.handyman.dsl.Forkprocess;
import in.handyman.dsl.GooglecalPUT;
import in.handyman.dsl.GooglecontactPUT;
import in.handyman.dsl.GooglecontactSelectAll;
import in.handyman.dsl.LoadCsv;
import in.handyman.dsl.Rest;
import in.handyman.dsl.RestPart;
import in.handyman.dsl.SendMail;
import in.handyman.dsl.SlackPUT;
import in.handyman.dsl.SmsLeadSms;
import in.handyman.dsl.Transform;
import in.handyman.dsl.TrelloGET;
import in.handyman.dsl.TrelloPUT;
import in.handyman.dsl.Try;
import in.handyman.dsl.Updatedaudit;
import in.handyman.dsl.WriteCsv;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DslFactoryImpl extends EFactoryImpl implements DslFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static DslFactory init()
  {
    try
    {
      DslFactory theDslFactory = (DslFactory)EPackage.Registry.INSTANCE.getEFactory(DslPackage.eNS_URI);
      if (theDslFactory != null)
      {
        return theDslFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new DslFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DslFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case DslPackage.PROCESS: return createProcess();
      case DslPackage.TRY: return createTry();
      case DslPackage.FINALLY: return createFinally();
      case DslPackage.CATCH: return createCatch();
      case DslPackage.ACTION: return createAction();
      case DslPackage.ES_UPDATE: return createEsUpdate();
      case DslPackage.ES_ACCUMULATE: return createEsAccumulate();
      case DslPackage.ELASTIC_FBC_LEAD: return createElasticFBCLead();
      case DslPackage.ELASTIC_GET: return createElasticGET();
      case DslPackage.EXEC_JAVA: return createExecJava();
      case DslPackage.FIREBASE_DATABASE_PUT: return createFirebaseDatabasePut();
      case DslPackage.FIREBASE_REACTIVE_NOTIFICATION: return createFirebaseReactiveNotification();
      case DslPackage.SMS_LEAD_SMS: return createSmsLeadSms();
      case DslPackage.ABORT: return createAbort();
      case DslPackage.GOOGLECONTACT_SELECT_ALL: return createGooglecontactSelectAll();
      case DslPackage.SEND_MAIL: return createSendMail();
      case DslPackage.GOOGLECONTACT_PUT: return createGooglecontactPUT();
      case DslPackage.GOOGLECAL_PUT: return createGooglecalPUT();
      case DslPackage.FBC_LEAD: return createFBCLead();
      case DslPackage.FB_FORM_DOWNLOAD: return createFBFormDownload();
      case DslPackage.DROPFILE: return createDropfile();
      case DslPackage.DOOZLE: return createDoozle();
      case DslPackage.REST: return createRest();
      case DslPackage.REST_PART: return createRestPart();
      case DslPackage.TRELLO_GET: return createTrelloGET();
      case DslPackage.TRELLO_PUT: return createTrelloPUT();
      case DslPackage.FETCH: return createFetch();
      case DslPackage.CALLPROCESS: return createCallprocess();
      case DslPackage.FORKPROCESS: return createForkprocess();
      case DslPackage.UPDATEDAUDIT: return createUpdatedaudit();
      case DslPackage.CLICK_SEND_SMS: return createClickSendSms();
      case DslPackage.SLACK_PUT: return createSlackPUT();
      case DslPackage.COPYDATA: return createCopydata();
      case DslPackage.WRITE_CSV: return createWriteCsv();
      case DslPackage.LOAD_CSV: return createLoadCsv();
      case DslPackage.TRANSFORM: return createTransform();
      case DslPackage.EXPRESSION: return createExpression();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public in.handyman.dsl.Process createProcess()
  {
    ProcessImpl process = new ProcessImpl();
    return process;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Try createTry()
  {
    TryImpl try_ = new TryImpl();
    return try_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Finally createFinally()
  {
    FinallyImpl finally_ = new FinallyImpl();
    return finally_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Catch createCatch()
  {
    CatchImpl catch_ = new CatchImpl();
    return catch_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Action createAction()
  {
    ActionImpl action = new ActionImpl();
    return action;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EsUpdate createEsUpdate()
  {
    EsUpdateImpl esUpdate = new EsUpdateImpl();
    return esUpdate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EsAccumulate createEsAccumulate()
  {
    EsAccumulateImpl esAccumulate = new EsAccumulateImpl();
    return esAccumulate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElasticFBCLead createElasticFBCLead()
  {
    ElasticFBCLeadImpl elasticFBCLead = new ElasticFBCLeadImpl();
    return elasticFBCLead;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElasticGET createElasticGET()
  {
    ElasticGETImpl elasticGET = new ElasticGETImpl();
    return elasticGET;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExecJava createExecJava()
  {
    ExecJavaImpl execJava = new ExecJavaImpl();
    return execJava;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FirebaseDatabasePut createFirebaseDatabasePut()
  {
    FirebaseDatabasePutImpl firebaseDatabasePut = new FirebaseDatabasePutImpl();
    return firebaseDatabasePut;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FirebaseReactiveNotification createFirebaseReactiveNotification()
  {
    FirebaseReactiveNotificationImpl firebaseReactiveNotification = new FirebaseReactiveNotificationImpl();
    return firebaseReactiveNotification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SmsLeadSms createSmsLeadSms()
  {
    SmsLeadSmsImpl smsLeadSms = new SmsLeadSmsImpl();
    return smsLeadSms;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Abort createAbort()
  {
    AbortImpl abort = new AbortImpl();
    return abort;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GooglecontactSelectAll createGooglecontactSelectAll()
  {
    GooglecontactSelectAllImpl googlecontactSelectAll = new GooglecontactSelectAllImpl();
    return googlecontactSelectAll;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SendMail createSendMail()
  {
    SendMailImpl sendMail = new SendMailImpl();
    return sendMail;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GooglecontactPUT createGooglecontactPUT()
  {
    GooglecontactPUTImpl googlecontactPUT = new GooglecontactPUTImpl();
    return googlecontactPUT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GooglecalPUT createGooglecalPUT()
  {
    GooglecalPUTImpl googlecalPUT = new GooglecalPUTImpl();
    return googlecalPUT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FBCLead createFBCLead()
  {
    FBCLeadImpl fbcLead = new FBCLeadImpl();
    return fbcLead;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FBFormDownload createFBFormDownload()
  {
    FBFormDownloadImpl fbFormDownload = new FBFormDownloadImpl();
    return fbFormDownload;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Dropfile createDropfile()
  {
    DropfileImpl dropfile = new DropfileImpl();
    return dropfile;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Doozle createDoozle()
  {
    DoozleImpl doozle = new DoozleImpl();
    return doozle;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Rest createRest()
  {
    RestImpl rest = new RestImpl();
    return rest;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RestPart createRestPart()
  {
    RestPartImpl restPart = new RestPartImpl();
    return restPart;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TrelloGET createTrelloGET()
  {
    TrelloGETImpl trelloGET = new TrelloGETImpl();
    return trelloGET;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TrelloPUT createTrelloPUT()
  {
    TrelloPUTImpl trelloPUT = new TrelloPUTImpl();
    return trelloPUT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Fetch createFetch()
  {
    FetchImpl fetch = new FetchImpl();
    return fetch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Callprocess createCallprocess()
  {
    CallprocessImpl callprocess = new CallprocessImpl();
    return callprocess;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Forkprocess createForkprocess()
  {
    ForkprocessImpl forkprocess = new ForkprocessImpl();
    return forkprocess;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Updatedaudit createUpdatedaudit()
  {
    UpdatedauditImpl updatedaudit = new UpdatedauditImpl();
    return updatedaudit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClickSendSms createClickSendSms()
  {
    ClickSendSmsImpl clickSendSms = new ClickSendSmsImpl();
    return clickSendSms;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SlackPUT createSlackPUT()
  {
    SlackPUTImpl slackPUT = new SlackPUTImpl();
    return slackPUT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Copydata createCopydata()
  {
    CopydataImpl copydata = new CopydataImpl();
    return copydata;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WriteCsv createWriteCsv()
  {
    WriteCsvImpl writeCsv = new WriteCsvImpl();
    return writeCsv;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LoadCsv createLoadCsv()
  {
    LoadCsvImpl loadCsv = new LoadCsvImpl();
    return loadCsv;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Transform createTransform()
  {
    TransformImpl transform = new TransformImpl();
    return transform;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression createExpression()
  {
    ExpressionImpl expression = new ExpressionImpl();
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DslPackage getDslPackage()
  {
    return (DslPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static DslPackage getPackage()
  {
    return DslPackage.eINSTANCE;
  }

} //DslFactoryImpl
