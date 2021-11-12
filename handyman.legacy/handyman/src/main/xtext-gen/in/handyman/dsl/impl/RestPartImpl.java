/**
 * generated by Xtext 2.16.0
 */
package in.handyman.dsl.impl;

import in.handyman.dsl.DslPackage;
import in.handyman.dsl.RestPart;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rest Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link in.handyman.dsl.impl.RestPartImpl#getPartName <em>Part Name</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.RestPartImpl#getPartData <em>Part Data</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RestPartImpl extends MinimalEObjectImpl.Container implements RestPart
{
  /**
   * The default value of the '{@link #getPartName() <em>Part Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPartName()
   * @generated
   * @ordered
   */
  protected static final String PART_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPartName() <em>Part Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPartName()
   * @generated
   * @ordered
   */
  protected String partName = PART_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getPartData() <em>Part Data</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPartData()
   * @generated
   * @ordered
   */
  protected static final String PART_DATA_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPartData() <em>Part Data</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPartData()
   * @generated
   * @ordered
   */
  protected String partData = PART_DATA_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RestPartImpl()
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
    return DslPackage.Literals.REST_PART;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPartName()
  {
    return partName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPartName(String newPartName)
  {
    String oldPartName = partName;
    partName = newPartName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.REST_PART__PART_NAME, oldPartName, partName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPartData()
  {
    return partData;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPartData(String newPartData)
  {
    String oldPartData = partData;
    partData = newPartData;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.REST_PART__PART_DATA, oldPartData, partData));
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
      case DslPackage.REST_PART__PART_NAME:
        return getPartName();
      case DslPackage.REST_PART__PART_DATA:
        return getPartData();
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
      case DslPackage.REST_PART__PART_NAME:
        setPartName((String)newValue);
        return;
      case DslPackage.REST_PART__PART_DATA:
        setPartData((String)newValue);
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
      case DslPackage.REST_PART__PART_NAME:
        setPartName(PART_NAME_EDEFAULT);
        return;
      case DslPackage.REST_PART__PART_DATA:
        setPartData(PART_DATA_EDEFAULT);
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
      case DslPackage.REST_PART__PART_NAME:
        return PART_NAME_EDEFAULT == null ? partName != null : !PART_NAME_EDEFAULT.equals(partName);
      case DslPackage.REST_PART__PART_DATA:
        return PART_DATA_EDEFAULT == null ? partData != null : !PART_DATA_EDEFAULT.equals(partData);
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
    result.append(" (partName: ");
    result.append(partName);
    result.append(", partData: ");
    result.append(partData);
    result.append(')');
    return result.toString();
  }

} //RestPartImpl