/**
 * generated by Xtext 2.16.0
 */
package in.handyman.dsl.impl;

import in.handyman.dsl.DslPackage;
import in.handyman.dsl.FBFormDownload;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>FB Form Download</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link in.handyman.dsl.impl.FBFormDownloadImpl#getAccessToken <em>Access Token</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.FBFormDownloadImpl#getAppSecret <em>App Secret</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.FBFormDownloadImpl#getAccountId <em>Account Id</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.FBFormDownloadImpl#getFormId <em>Form Id</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.FBFormDownloadImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.FBFormDownloadImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FBFormDownloadImpl extends ActionImpl implements FBFormDownload
{
  /**
   * The default value of the '{@link #getAccessToken() <em>Access Token</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAccessToken()
   * @generated
   * @ordered
   */
  protected static final String ACCESS_TOKEN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAccessToken() <em>Access Token</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAccessToken()
   * @generated
   * @ordered
   */
  protected String accessToken = ACCESS_TOKEN_EDEFAULT;

  /**
   * The default value of the '{@link #getAppSecret() <em>App Secret</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAppSecret()
   * @generated
   * @ordered
   */
  protected static final String APP_SECRET_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAppSecret() <em>App Secret</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAppSecret()
   * @generated
   * @ordered
   */
  protected String appSecret = APP_SECRET_EDEFAULT;

  /**
   * The default value of the '{@link #getAccountId() <em>Account Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAccountId()
   * @generated
   * @ordered
   */
  protected static final String ACCOUNT_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAccountId() <em>Account Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAccountId()
   * @generated
   * @ordered
   */
  protected String accountId = ACCOUNT_ID_EDEFAULT;

  /**
   * The default value of the '{@link #getFormId() <em>Form Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFormId()
   * @generated
   * @ordered
   */
  protected static final String FORM_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFormId() <em>Form Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFormId()
   * @generated
   * @ordered
   */
  protected String formId = FORM_ID_EDEFAULT;

  /**
   * The default value of the '{@link #getTarget() <em>Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected static final String TARGET_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected String target = TARGET_EDEFAULT;

  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final String VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected String value = VALUE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FBFormDownloadImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return DslPackage.Literals.FB_FORM_DOWNLOAD;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAccessToken()
  {
    return accessToken;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAccessToken(String newAccessToken)
  {
    String oldAccessToken = accessToken;
    accessToken = newAccessToken;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.FB_FORM_DOWNLOAD__ACCESS_TOKEN, oldAccessToken, accessToken));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAppSecret()
  {
    return appSecret;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAppSecret(String newAppSecret)
  {
    String oldAppSecret = appSecret;
    appSecret = newAppSecret;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.FB_FORM_DOWNLOAD__APP_SECRET, oldAppSecret, appSecret));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAccountId()
  {
    return accountId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAccountId(String newAccountId)
  {
    String oldAccountId = accountId;
    accountId = newAccountId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.FB_FORM_DOWNLOAD__ACCOUNT_ID, oldAccountId, accountId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFormId()
  {
    return formId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFormId(String newFormId)
  {
    String oldFormId = formId;
    formId = newFormId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.FB_FORM_DOWNLOAD__FORM_ID, oldFormId, formId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(String newTarget)
  {
    String oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.FB_FORM_DOWNLOAD__TARGET, oldTarget, target));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(String newValue)
  {
    String oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.FB_FORM_DOWNLOAD__VALUE, oldValue, value));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case DslPackage.FB_FORM_DOWNLOAD__ACCESS_TOKEN:
        return getAccessToken();
      case DslPackage.FB_FORM_DOWNLOAD__APP_SECRET:
        return getAppSecret();
      case DslPackage.FB_FORM_DOWNLOAD__ACCOUNT_ID:
        return getAccountId();
      case DslPackage.FB_FORM_DOWNLOAD__FORM_ID:
        return getFormId();
      case DslPackage.FB_FORM_DOWNLOAD__TARGET:
        return getTarget();
      case DslPackage.FB_FORM_DOWNLOAD__VALUE:
        return getValue();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case DslPackage.FB_FORM_DOWNLOAD__ACCESS_TOKEN:
        setAccessToken((String)newValue);
        return;
      case DslPackage.FB_FORM_DOWNLOAD__APP_SECRET:
        setAppSecret((String)newValue);
        return;
      case DslPackage.FB_FORM_DOWNLOAD__ACCOUNT_ID:
        setAccountId((String)newValue);
        return;
      case DslPackage.FB_FORM_DOWNLOAD__FORM_ID:
        setFormId((String)newValue);
        return;
      case DslPackage.FB_FORM_DOWNLOAD__TARGET:
        setTarget((String)newValue);
        return;
      case DslPackage.FB_FORM_DOWNLOAD__VALUE:
        setValue((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case DslPackage.FB_FORM_DOWNLOAD__ACCESS_TOKEN:
        setAccessToken(ACCESS_TOKEN_EDEFAULT);
        return;
      case DslPackage.FB_FORM_DOWNLOAD__APP_SECRET:
        setAppSecret(APP_SECRET_EDEFAULT);
        return;
      case DslPackage.FB_FORM_DOWNLOAD__ACCOUNT_ID:
        setAccountId(ACCOUNT_ID_EDEFAULT);
        return;
      case DslPackage.FB_FORM_DOWNLOAD__FORM_ID:
        setFormId(FORM_ID_EDEFAULT);
        return;
      case DslPackage.FB_FORM_DOWNLOAD__TARGET:
        setTarget(TARGET_EDEFAULT);
        return;
      case DslPackage.FB_FORM_DOWNLOAD__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case DslPackage.FB_FORM_DOWNLOAD__ACCESS_TOKEN:
        return ACCESS_TOKEN_EDEFAULT == null ? accessToken != null : !ACCESS_TOKEN_EDEFAULT.equals(accessToken);
      case DslPackage.FB_FORM_DOWNLOAD__APP_SECRET:
        return APP_SECRET_EDEFAULT == null ? appSecret != null : !APP_SECRET_EDEFAULT.equals(appSecret);
      case DslPackage.FB_FORM_DOWNLOAD__ACCOUNT_ID:
        return ACCOUNT_ID_EDEFAULT == null ? accountId != null : !ACCOUNT_ID_EDEFAULT.equals(accountId);
      case DslPackage.FB_FORM_DOWNLOAD__FORM_ID:
        return FORM_ID_EDEFAULT == null ? formId != null : !FORM_ID_EDEFAULT.equals(formId);
      case DslPackage.FB_FORM_DOWNLOAD__TARGET:
        return TARGET_EDEFAULT == null ? target != null : !TARGET_EDEFAULT.equals(target);
      case DslPackage.FB_FORM_DOWNLOAD__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (accessToken: ");
    result.append(accessToken);
    result.append(", appSecret: ");
    result.append(appSecret);
    result.append(", accountId: ");
    result.append(accountId);
    result.append(", formId: ");
    result.append(formId);
    result.append(", target: ");
    result.append(target);
    result.append(", value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

} //FBFormDownloadImpl