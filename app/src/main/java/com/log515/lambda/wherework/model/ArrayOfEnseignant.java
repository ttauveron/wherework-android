package com.log515.lambda.wherework.model;
//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 4.5.8.1
//
// Created by Quasar Development at 06/09/2016
//
//---------------------------------------------------



import com.log515.lambda.wherework.soap.ExtendedSoapSerializationEnvelope;

import org.ksoap2.serialization.*;
import java.util.Vector;
import java.util.Hashtable;


public class ArrayOfEnseignant extends Vector<Enseignant> implements KvmSerializable
{
    
    public ArrayOfEnseignant(){}
    
    public ArrayOfEnseignant(java.lang.Object inObj, ExtendedSoapSerializationEnvelope __envelope)
    {
        if (inObj == null)
            return;
        SoapObject soapObject=(SoapObject)inObj;
        int size = soapObject.getPropertyCount();
        for (int i0=0;i0< size;i0++)
        {
            java.lang.Object obj = soapObject.getProperty(i0);
            if (obj!=null && obj instanceof AttributeContainer)
            {
                AttributeContainer j =(AttributeContainer) soapObject.getProperty(i0);
                Enseignant j1= (Enseignant)__envelope.get(j,Enseignant.class);
                add(j1);
            }
        }
}
    
    @Override
    public java.lang.Object getProperty(int arg0) {
        return this.get(arg0)!=null?this.get(arg0):SoapPrimitive.NullNilElement;
    }
    
    @Override
    public int getPropertyCount() {
        return this.size();
    }
    
    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        info.name = "Enseignant";
        info.type = Enseignant.class;
    	info.namespace= "http://etsmtl.ca/";
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1) {
    }

}