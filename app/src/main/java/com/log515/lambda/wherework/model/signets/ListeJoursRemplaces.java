package com.log515.lambda.wherework.model.signets;
//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 4.5.8.1
//
// Created by Quasar Development at 06/09/2016
//
//---------------------------------------------------


import com.log515.lambda.wherework.soap.DonneesRetournees;
import com.log515.lambda.wherework.soap.ExtendedSoapSerializationEnvelope;

import java.util.Hashtable;
import org.ksoap2.serialization.*;

public class ListeJoursRemplaces extends DonneesRetournees implements KvmSerializable
{

    
    public ArrayOfJoursRemplaces listeJours;

    public ListeJoursRemplaces()
    {
    }

    public ListeJoursRemplaces(java.lang.Object paramObj, ExtendedSoapSerializationEnvelope __envelope)
    {
	    super(paramObj, __envelope);
	    if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;


        if(inObj instanceof SoapObject)
        {
            SoapObject soapObject=(SoapObject)inObj;
            int size = soapObject.getPropertyCount();
            for (int i0=0;i0< size;i0++)
            {
                //if you have compilation error here, please use a ksoap2.jar and ExKsoap2.jar from libs folder (in the generated zip file)
                PropertyInfo info=soapObject.getPropertyInfo(i0);
                java.lang.Object obj = info.getValue(); 
                if (info.name.equals("listeJours"))
                {
                    if(obj!=null)
                    {
                        java.lang.Object j = obj;
                        this.listeJours = new ArrayOfJoursRemplaces(j,__envelope);
                    }
                    continue;
                }

            }

        }



    }

    @Override
    public java.lang.Object getProperty(int propertyIndex) {
        int count = super.getPropertyCount();
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==count+0)
        {
            return this.listeJours!=null?this.listeJours:SoapPrimitive.NullSkip;
        }
        return super.getProperty(propertyIndex);
    }


    @Override
    public int getPropertyCount() {
        return super.getPropertyCount()+1;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        int count = super.getPropertyCount();
        if(propertyIndex==count+0)
        {
            info.type = PropertyInfo.VECTOR_CLASS;
            info.name = "listeJours";
            info.namespace= "http://etsmtl.ca/";
        }
        super.getPropertyInfo(propertyIndex,arg1,info);
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

}