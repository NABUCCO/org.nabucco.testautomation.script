/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.testautomation.script.facade.datatype.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.code.Code;
import org.nabucco.framework.base.facade.datatype.code.CodePath;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.testautomation.facade.datatype.property.PropertyList;

/**
 * MetadataLabel<p/>A qualifier for a Metadata-instance<p/>
 *
 * @author Steffen Schmidt, PRODYNA AG, 2010-04-07
 */
public class MetadataLabel extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,1;", "m0,1;", "m0,1;", "m0,1;" };

    public static final String ENVIRONMENTTYPE = "environmentType";

    public static final String RELEASETYPE = "releaseType";

    public static final String BRANDTYPE = "brandType";

    public static final String PROPERTYLIST = "propertyList";

    /** Environment of the TestConfiguration */
    private Code environmentType;

    private Long environmentTypeRefId;

    private static final String ENVIRONMENTTYPE_CODEPATH = "nabucco.testautomation.environment";

    /** Release of the TestConfiguration */
    private Code releaseType;

    private Long releaseTypeRefId;

    private static final String RELEASETYPE_CODEPATH = "nabucco.testautomation.release";

    /** Brand of the TestConfiguration */
    private Code brandType;

    private Long brandTypeRefId;

    private static final String BRANDTYPE_CODEPATH = "nabucco.testautomation.brand";

    private PropertyList propertyList;

    private Long propertyListRefId;

    /** Constructs a new MetadataLabel instance. */
    public MetadataLabel() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the MetadataLabel.
     */
    protected void cloneObject(MetadataLabel clone) {
        super.cloneObject(clone);
        if ((this.getEnvironmentType() != null)) {
            clone.setEnvironmentType(this.getEnvironmentType().cloneObject());
        }
        if ((this.getEnvironmentTypeRefId() != null)) {
            clone.setEnvironmentTypeRefId(this.getEnvironmentTypeRefId());
        }
        if ((this.getReleaseType() != null)) {
            clone.setReleaseType(this.getReleaseType().cloneObject());
        }
        if ((this.getReleaseTypeRefId() != null)) {
            clone.setReleaseTypeRefId(this.getReleaseTypeRefId());
        }
        if ((this.getBrandType() != null)) {
            clone.setBrandType(this.getBrandType().cloneObject());
        }
        if ((this.getBrandTypeRefId() != null)) {
            clone.setBrandTypeRefId(this.getBrandTypeRefId());
        }
        if ((this.getPropertyList() != null)) {
            clone.setPropertyList(this.getPropertyList().cloneObject());
        }
        if ((this.getPropertyListRefId() != null)) {
            clone.setPropertyListRefId(this.getPropertyListRefId());
        }
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(NabuccoDatatype.class)
                .getPropertyMap());
        propertyMap.put(ENVIRONMENTTYPE, PropertyDescriptorSupport.createDatatype(ENVIRONMENTTYPE,
                Code.class, 2, PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPONENT));
        propertyMap.put(RELEASETYPE, PropertyDescriptorSupport.createDatatype(RELEASETYPE,
                Code.class, 3, PROPERTY_CONSTRAINTS[1], false, PropertyAssociationType.COMPONENT));
        propertyMap.put(BRANDTYPE, PropertyDescriptorSupport.createDatatype(BRANDTYPE, Code.class,
                4, PROPERTY_CONSTRAINTS[2], false, PropertyAssociationType.COMPONENT));
        propertyMap.put(PROPERTYLIST, PropertyDescriptorSupport.createDatatype(PROPERTYLIST,
                PropertyList.class, 5, PROPERTY_CONSTRAINTS[3], false,
                PropertyAssociationType.COMPONENT));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty> getProperties() {
        List<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(MetadataLabel.getPropertyDescriptor(ENVIRONMENTTYPE),
                this.environmentType, this.environmentTypeRefId));
        properties.add(super.createProperty(MetadataLabel.getPropertyDescriptor(RELEASETYPE),
                this.releaseType, this.releaseTypeRefId));
        properties.add(super.createProperty(MetadataLabel.getPropertyDescriptor(BRANDTYPE),
                this.brandType, this.brandTypeRefId));
        properties.add(super.createProperty(MetadataLabel.getPropertyDescriptor(PROPERTYLIST),
                this.propertyList, this.propertyListRefId));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(ENVIRONMENTTYPE) && (property.getType() == Code.class))) {
            this.setEnvironmentType(((Code) property.getInstance()));
            return true;
        } else if ((property.getName().equals(RELEASETYPE) && (property.getType() == Code.class))) {
            this.setReleaseType(((Code) property.getInstance()));
            return true;
        } else if ((property.getName().equals(BRANDTYPE) && (property.getType() == Code.class))) {
            this.setBrandType(((Code) property.getInstance()));
            return true;
        } else if ((property.getName().equals(PROPERTYLIST) && (property.getType() == PropertyList.class))) {
            this.setPropertyList(((PropertyList) property.getInstance()));
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if ((this == obj)) {
            return true;
        }
        if ((obj == null)) {
            return false;
        }
        if ((this.getClass() != obj.getClass())) {
            return false;
        }
        if ((!super.equals(obj))) {
            return false;
        }
        final MetadataLabel other = ((MetadataLabel) obj);
        if ((this.environmentType == null)) {
            if ((other.environmentType != null))
                return false;
        } else if ((!this.environmentType.equals(other.environmentType)))
            return false;
        if ((this.environmentTypeRefId == null)) {
            if ((other.environmentTypeRefId != null))
                return false;
        } else if ((!this.environmentTypeRefId.equals(other.environmentTypeRefId)))
            return false;
        if ((this.releaseType == null)) {
            if ((other.releaseType != null))
                return false;
        } else if ((!this.releaseType.equals(other.releaseType)))
            return false;
        if ((this.releaseTypeRefId == null)) {
            if ((other.releaseTypeRefId != null))
                return false;
        } else if ((!this.releaseTypeRefId.equals(other.releaseTypeRefId)))
            return false;
        if ((this.brandType == null)) {
            if ((other.brandType != null))
                return false;
        } else if ((!this.brandType.equals(other.brandType)))
            return false;
        if ((this.brandTypeRefId == null)) {
            if ((other.brandTypeRefId != null))
                return false;
        } else if ((!this.brandTypeRefId.equals(other.brandTypeRefId)))
            return false;
        if ((this.propertyList == null)) {
            if ((other.propertyList != null))
                return false;
        } else if ((!this.propertyList.equals(other.propertyList)))
            return false;
        if ((this.propertyListRefId == null)) {
            if ((other.propertyListRefId != null))
                return false;
        } else if ((!this.propertyListRefId.equals(other.propertyListRefId)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.environmentType == null) ? 0 : this.environmentType
                .hashCode()));
        result = ((PRIME * result) + ((this.environmentTypeRefId == null) ? 0
                : this.environmentTypeRefId.hashCode()));
        result = ((PRIME * result) + ((this.releaseType == null) ? 0 : this.releaseType.hashCode()));
        result = ((PRIME * result) + ((this.releaseTypeRefId == null) ? 0 : this.releaseTypeRefId
                .hashCode()));
        result = ((PRIME * result) + ((this.brandType == null) ? 0 : this.brandType.hashCode()));
        result = ((PRIME * result) + ((this.brandTypeRefId == null) ? 0 : this.brandTypeRefId
                .hashCode()));
        result = ((PRIME * result) + ((this.propertyList == null) ? 0 : this.propertyList
                .hashCode()));
        result = ((PRIME * result) + ((this.propertyListRefId == null) ? 0 : this.propertyListRefId
                .hashCode()));
        return result;
    }

    @Override
    public MetadataLabel cloneObject() {
        MetadataLabel clone = new MetadataLabel();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Environment of the TestConfiguration
     *
     * @param environmentType the Code.
     */
    public void setEnvironmentType(Code environmentType) {
        this.environmentType = environmentType;
        if ((environmentType != null)) {
            this.setEnvironmentTypeRefId(environmentType.getId());
        } else {
            this.setEnvironmentTypeRefId(null);
        }
    }

    /**
     * Environment of the TestConfiguration
     *
     * @return the Code.
     */
    public Code getEnvironmentType() {
        return this.environmentType;
    }

    /**
     * Getter for the EnvironmentTypeRefId.
     *
     * @return the Long.
     */
    public Long getEnvironmentTypeRefId() {
        return this.environmentTypeRefId;
    }

    /**
     * Setter for the EnvironmentTypeRefId.
     *
     * @param environmentTypeRefId the Long.
     */
    public void setEnvironmentTypeRefId(Long environmentTypeRefId) {
        this.environmentTypeRefId = environmentTypeRefId;
    }

    /**
     * Release of the TestConfiguration
     *
     * @param releaseType the Code.
     */
    public void setReleaseType(Code releaseType) {
        this.releaseType = releaseType;
        if ((releaseType != null)) {
            this.setReleaseTypeRefId(releaseType.getId());
        } else {
            this.setReleaseTypeRefId(null);
        }
    }

    /**
     * Release of the TestConfiguration
     *
     * @return the Code.
     */
    public Code getReleaseType() {
        return this.releaseType;
    }

    /**
     * Getter for the ReleaseTypeRefId.
     *
     * @return the Long.
     */
    public Long getReleaseTypeRefId() {
        return this.releaseTypeRefId;
    }

    /**
     * Setter for the ReleaseTypeRefId.
     *
     * @param releaseTypeRefId the Long.
     */
    public void setReleaseTypeRefId(Long releaseTypeRefId) {
        this.releaseTypeRefId = releaseTypeRefId;
    }

    /**
     * Brand of the TestConfiguration
     *
     * @param brandType the Code.
     */
    public void setBrandType(Code brandType) {
        this.brandType = brandType;
        if ((brandType != null)) {
            this.setBrandTypeRefId(brandType.getId());
        } else {
            this.setBrandTypeRefId(null);
        }
    }

    /**
     * Brand of the TestConfiguration
     *
     * @return the Code.
     */
    public Code getBrandType() {
        return this.brandType;
    }

    /**
     * Getter for the BrandTypeRefId.
     *
     * @return the Long.
     */
    public Long getBrandTypeRefId() {
        return this.brandTypeRefId;
    }

    /**
     * Setter for the BrandTypeRefId.
     *
     * @param brandTypeRefId the Long.
     */
    public void setBrandTypeRefId(Long brandTypeRefId) {
        this.brandTypeRefId = brandTypeRefId;
    }

    /**
     * Missing description at method setPropertyList.
     *
     * @param propertyList the PropertyList.
     */
    public void setPropertyList(PropertyList propertyList) {
        this.propertyList = propertyList;
        if ((propertyList != null)) {
            this.setPropertyListRefId(propertyList.getId());
        } else {
            this.setPropertyListRefId(null);
        }
    }

    /**
     * Missing description at method getPropertyList.
     *
     * @return the PropertyList.
     */
    public PropertyList getPropertyList() {
        return this.propertyList;
    }

    /**
     * Getter for the PropertyListRefId.
     *
     * @return the Long.
     */
    public Long getPropertyListRefId() {
        return this.propertyListRefId;
    }

    /**
     * Setter for the PropertyListRefId.
     *
     * @param propertyListRefId the Long.
     */
    public void setPropertyListRefId(Long propertyListRefId) {
        this.propertyListRefId = propertyListRefId;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(MetadataLabel.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(MetadataLabel.class).getAllProperties();
    }

    /**
     * Getter for the EnvironmentTypeCodePath.
     *
     * @return the CodePath.
     */
    public static CodePath getEnvironmentTypeCodePath() {
        return new CodePath(ENVIRONMENTTYPE_CODEPATH);
    }

    /**
     * Getter for the ReleaseTypeCodePath.
     *
     * @return the CodePath.
     */
    public static CodePath getReleaseTypeCodePath() {
        return new CodePath(RELEASETYPE_CODEPATH);
    }

    /**
     * Getter for the BrandTypeCodePath.
     *
     * @return the CodePath.
     */
    public static CodePath getBrandTypeCodePath() {
        return new CodePath(BRANDTYPE_CODEPATH);
    }
}
