/*
 * NOTE: This copyright does *not* cover user programs that use HQ
 * program services by normal system calls through the application
 * program interfaces provided as part of the Hyperic Plug-in Development
 * Kit or the Hyperic Client Development Kit - this is merely considered
 * normal use of the program, and does *not* fall under the heading of
 * "derived work".
 * 
 * Copyright (C) [2004, 2005, 2006], Hyperic, Inc.
 * This file is part of HQ.
 * 
 * HQ is free software; you can redistribute it and/or modify
 * it under the terms version 2 of the GNU General Public License as
 * published by the Free Software Foundation. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 */

package org.hyperic.hq.authz.server.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.hyperic.hq.authz.shared.ResourceTypeValue;

public class ResourceType extends AuthzNamedBean
{
    private Integer    _cid;
    private Resource   _resource;
    private boolean    _system = false;
    private Collection _operations = new ArrayList();

    private ResourceTypeValue _resourceTypeValue = new ResourceTypeValue();

    protected ResourceType() {
        super();
    }

    ResourceType(String name, Resource resource, boolean fsystem) {
        super(name);
        _resource = resource;
        _system   = fsystem;
    }

    public Integer getCid() {
        return _cid;
    }

    protected void setCid(Integer val) {
        _cid = val;
    }

    public Resource getResource() {
        return _resource;
    }

    protected void setResource(Resource val) {
        _resource = val;
    }

    public boolean isSystem() {
        return _system;
    }

    protected void setSystem(boolean val) {
        _system = val;
    }

    protected Collection getOperationsBag() {
        return _operations;
    }

    protected void setOperationsBag(Collection val) {
        _operations = val;
    }
    
    void setOperations(Collection val) {
        _operations = val;
    }
   
    public Collection getOperations() {
        return Collections.unmodifiableCollection(_operations);
    }

    /**
     * @deprecated use (this) ResourceType instead
     */
    public ResourceTypeValue getResourceTypeValue() {
        _resourceTypeValue.setId(getId());
        _resourceTypeValue.setName(getName());
        _resourceTypeValue.setSystem(isSystem());
        
        // Clear out the operation values first
        _resourceTypeValue.removeAllOperationValues();
        if (getOperations() != null) {
            for (Iterator it = getOperations().iterator(); it.hasNext(); ) {
                Operation op = (Operation) it.next();
                _resourceTypeValue.addOperationValue(op.getOperationValue());
            }
        }
        return _resourceTypeValue;
    }

    public Object getValueObject() {
        return getResourceTypeValue();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ResourceType) && super.equals(obj);
    }
}
