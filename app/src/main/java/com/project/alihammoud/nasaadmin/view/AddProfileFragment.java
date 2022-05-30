package com.project.alihammoud.nasaadmin.view;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.project.alihammoud.nasaadmin.R;
import com.project.alihammoud.nasaadmin.controller.ApiClient;
import com.project.alihammoud.nasaadmin.model.ProfileDTO;

import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddProfileFragment extends Fragment implements View.OnClickListener {

    public Button cancel, add, pick1, pick2, pick3, pick4, pick5, pick6, pick7, pick8, pick9, pick10;
    public Button pick11, pick12, pick13, pick14, pick15, pick16, pick17, pick18, pick19, pick20;
    public Button pick21, pick22, pick23, pick24, pick25;
    public EditText int1, int2, int3, int4, int5, int6, int7, int8, int9, int10;
    public EditText int11, int12, int13, int14, int15, int16, int17, int18, int19, int20;
    public EditText int21, int22, int23, int24, int25;

    boolean dpick1 =false , dpick2=false , dpick3=false, dpick4=false, dpick5=false, dpick6=false, dpick7=false, dpick8=false ,
            dpick9=false, dpick10=false, dpick11=false, dpick12 =false, dpick13=false, dpick14=false, dpick15=false, dpick16=false,
            dpick17=false, dpick18=false , dpick19=false, dpick20=false, dpick21=false , dpick22=false, dpick23=false, dpick24=false, dpick25=false;

    LinkedList<String> intensityList = new LinkedList<>();
    LinkedList<String> colorList = new LinkedList<>();

    public String[] timeList = new String[]{"06:00:00", "06:30:00", "07:00:00", "06:30:00", "07:30:00", "08:00:00", "08:30:00"
            , "09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00"
            , "14:00:00", "14:30:00", "15:00:00", "15:30:00", "16:00:00","16:30:00", "17:00:00","17:30:00", "18:00:00"};
    public EditText eName;
    public Context context;
    boolean no = false;
    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_profile, container, false);
        context = view.getContext();

        eName = view.findViewById(R.id.name);

        pick1 = view.findViewById(R.id.pick1);
        pick1.setOnClickListener(this);
        pick2 = view.findViewById(R.id.pick2);
        pick2.setOnClickListener(this);
        pick3 = view.findViewById(R.id.pick3);
        pick3.setOnClickListener(this);
        pick4 = view.findViewById(R.id.pick4);
        pick4.setOnClickListener(this);
        pick5 = view.findViewById(R.id.pick5);
        pick5.setOnClickListener(this);
        pick6 = view.findViewById(R.id.pick6);
        pick6.setOnClickListener(this);
        pick7 = view.findViewById(R.id.pick7);
        pick7.setOnClickListener(this);
        pick8 = view.findViewById(R.id.pick8);
        pick8.setOnClickListener(this);
        pick9 = view.findViewById(R.id.pick9);
        pick9.setOnClickListener(this);
        pick10 = view.findViewById(R.id.pick10);
        pick10.setOnClickListener(this);
        pick11 = view.findViewById(R.id.pick11);
        pick11.setOnClickListener(this);
        pick12 = view.findViewById(R.id.pick12);
        pick12.setOnClickListener(this);
        pick13 = view.findViewById(R.id.pick13);
        pick13.setOnClickListener(this);
        pick14 = view.findViewById(R.id.pick14);
        pick14.setOnClickListener(this);
        pick15 = view.findViewById(R.id.pick15);
        pick15.setOnClickListener(this);
        pick16 = view.findViewById(R.id.pick16);
        pick16.setOnClickListener(this);
        pick17 = view.findViewById(R.id.pick17);
        pick17.setOnClickListener(this);
        pick18 = view.findViewById(R.id.pick18);
        pick18.setOnClickListener(this);
        pick19 = view.findViewById(R.id.pick19);
        pick19.setOnClickListener(this);
        pick20 = view.findViewById(R.id.pick20);
        pick20.setOnClickListener(this);
        pick21 = view.findViewById(R.id.pick21);
        pick21.setOnClickListener(this);
        pick22 = view.findViewById(R.id.pick22);
        pick22.setOnClickListener(this);
        pick23 = view.findViewById(R.id.pick23);
        pick23.setOnClickListener(this);
        pick24 = view.findViewById(R.id.pick24);
        pick24.setOnClickListener(this);
        pick25 = view.findViewById(R.id.pick25);
        pick25.setOnClickListener(this);

        int1 = view.findViewById(R.id.int1);
        int2 = view.findViewById(R.id.int2);
        int3 = view.findViewById(R.id.int3);
        int4 = view.findViewById(R.id.int4);
        int5 = view.findViewById(R.id.int5);
        int6 = view.findViewById(R.id.int6);
        int7 = view.findViewById(R.id.int7);
        int8 = view.findViewById(R.id.int8);
        int9 = view.findViewById(R.id.int9);
        int10 = view.findViewById(R.id.int10);
        int11 = view.findViewById(R.id.int11);
        int12 = view.findViewById(R.id.int12);
        int13 = view.findViewById(R.id.int13);
        int14 = view.findViewById(R.id.int14);
        int15 = view.findViewById(R.id.int15);
        int16 = view.findViewById(R.id.int16);
        int17 = view.findViewById(R.id.int17);
        int18 = view.findViewById(R.id.int18);
        int19 = view.findViewById(R.id.int19);
        int20 = view.findViewById(R.id.int20);
        int21 = view.findViewById(R.id.int21);
        int22 = view.findViewById(R.id.int22);
        int23 = view.findViewById(R.id.int23);
        int24 = view.findViewById(R.id.int24);
        int25 = view.findViewById(R.id.int25);


        add = view.findViewById(R.id.add);
        add.setOnClickListener(this);

        cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);


        return view;
    }

    public void postProfiles(String name, boolean isPlant, LinkedList rhythmList, LinkedList intensityList, String[] timeList) {

        ProfileDTO profileDTO = new ProfileDTO(name, isPlant, rhythmList, intensityList, timeList);
        Call<ProfileDTO> profilesList = ApiClient.getService().postProfiles(profileDTO);

        profilesList.enqueue(new Callback<ProfileDTO>() {
            @Override
            public void onResponse(Call<ProfileDTO> call, Response<ProfileDTO> response) {
                Log.e("Success", "It Worked!");
                getActivity().getSupportFragmentManager().popBackStack();
            }

            @Override
            public void onFailure(Call<ProfileDTO> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }

    public void pickColor(int id) {
        ColorPickerDialogBuilder
                .with(context)
                .setTitle("Choose Color")
                //.initialColor(currentBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        //toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        Button coloredButton = view.findViewById(id);
                        coloredButton.setBackgroundColor(selectedColor);

                        switch (id) {
                            case R.id.pick1:
                                dpick1= true;
                                break;
                            case R.id.pick2:
                                dpick2= true;
                                break;
                            case R.id.pick3:
                                dpick3= true;
                                break;
                            case R.id.pick4:
                                dpick4= true;
                                break;
                            case R.id.pick5:
                                dpick5= true;
                                break;
                            case R.id.pick6:
                                dpick6= true;
                                break;
                            case R.id.pick7:
                                dpick7= true;
                                break;
                            case R.id.pick8:
                                dpick8= true;
                                break;
                            case R.id.pick9:
                                dpick9= true;
                                break;
                            case R.id.pick10:
                                dpick10= true;
                                break;
                            case R.id.pick11:
                                dpick11= true;
                                break;
                            case R.id.pick12:
                                dpick12= true;
                                break;
                            case R.id.pick13:
                                dpick13= true;
                                break;
                            case R.id.pick14:
                                dpick14= true;
                                break;
                            case R.id.pick15:
                                dpick15= true;
                                break;
                            case R.id.pick16:
                                dpick16= true;
                                break;
                            case R.id.pick17:
                                dpick17= true;
                                break;
                            case R.id.pick18:
                                dpick18= true;
                                break;
                            case R.id.pick19:
                                dpick19= true;
                                break;
                            case R.id.pick20:
                                dpick20= true;
                                break;
                            case R.id.pick21:
                                dpick21= true;
                                break;
                            case R.id.pick22:
                                dpick22= true;
                                break;
                            case R.id.pick23:
                                dpick23= true;
                                break;
                            case R.id.pick24:
                                dpick24= true;
                                break;
                            case R.id.pick25:
                                dpick25= true;
                                break;


                        }
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pick1:
            case R.id.pick2:
            case R.id.pick3:
            case R.id.pick4:
            case R.id.pick5:
            case R.id.pick6:
            case R.id.pick7:
            case R.id.pick8:
            case R.id.pick9:
            case R.id.pick10:
            case R.id.pick11:
            case R.id.pick12:
            case R.id.pick13:
            case R.id.pick14:
            case R.id.pick15:
            case R.id.pick16:
            case R.id.pick17:
            case R.id.pick18:
            case R.id.pick19:
            case R.id.pick20:
            case R.id.pick21:
            case R.id.pick22:
            case R.id.pick23:
            case R.id.pick24:
            case R.id.pick25:
                pickColor(v.getId());
                break;

            case R.id.add:

                if (!validateForm()) {
                    return;
                }

                String name = eName.getText().toString().trim();

                intensityList.add(int1.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick1.getBackground()).getColor()) );

                intensityList.add(int2.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick2.getBackground()).getColor()));

                intensityList.add(int3.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick3.getBackground()).getColor()));

                intensityList.add(int4.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick4.getBackground()).getColor()));

                intensityList.add(int5.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick5.getBackground()).getColor()));

                intensityList.add(int6.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick6.getBackground()).getColor()));

                intensityList.add(int7.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick7.getBackground()).getColor()));

                intensityList.add(int8.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick8.getBackground()).getColor()));

                intensityList.add(int9.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick9.getBackground()).getColor()));

                intensityList.add(int10.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick10.getBackground()).getColor()));

                intensityList.add(int11.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick11.getBackground()).getColor()));

                intensityList.add(int12.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick12.getBackground()).getColor()));

                intensityList.add(int13.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick13.getBackground()).getColor()));

                intensityList.add(int14.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick14.getBackground()).getColor()));

                intensityList.add(int15.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick15.getBackground()).getColor()));

                intensityList.add(int16.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick16.getBackground()).getColor()));

                intensityList.add(int17.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick17.getBackground()).getColor()));

                intensityList.add(int18.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick18.getBackground()).getColor()));

                intensityList.add(int19.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick19.getBackground()).getColor()));

                intensityList.add(int20.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick20.getBackground()).getColor()));

                intensityList.add(int21.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick21.getBackground()).getColor()));

                intensityList.add(int22.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick22.getBackground()).getColor()));

                intensityList.add(int23.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick23.getBackground()).getColor()));

                intensityList.add(int24.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick24.getBackground()).getColor()));

                intensityList.add(int25.getText().toString().trim());
                colorList.add(Integer.toHexString(((ColorDrawable) pick25.getBackground()).getColor()));

                postProfiles(name, no, colorList, intensityList, timeList);
                break;

            case R.id.cancel:
                getActivity().getSupportFragmentManager().popBackStack();
                break;


        }
    }


   private boolean validateForm() {
        boolean valid = true;

       if (  eName.getText().toString().trim().isEmpty()){
           eName.setError("Required.");
           valid = false;
       } else {
           eName.setError(null);
       }

       if (TextUtils.isEmpty(int1.getText().toString().trim()) ||
               Integer.parseInt(int1.getText().toString().trim()) > 100 ||
               Integer.parseInt(int1.getText().toString().trim()) < 0) {
           int1.setError("Required.");
           valid = false;
       } else {
           int1.setError(null);
       }

        if (TextUtils.isEmpty(int2.getText().toString().trim()) ||
                Integer.parseInt(int2.getText().toString().trim())>100 ||
                Integer.parseInt(int2.getText().toString().trim())<0) {
            int2.setError("Required.");
            valid = false;
        } else {
            int2.setError(null);
        }

        if (TextUtils.isEmpty(int2.getText().toString().trim()) ||
                Integer.parseInt(int2.getText().toString().trim())>100 ||
                Integer.parseInt(int2.getText().toString().trim())<0) {
            int2.setError("Required.");
            valid = false;
        } else {
            int2.setError(null);
        }

        if (TextUtils.isEmpty(int3.getText().toString().trim()) ||
                Integer.parseInt(int3.getText().toString().trim())>100 ||
                Integer.parseInt(int3.getText().toString().trim())<0) {
            int3.setError("Required.");
            valid = false;
        } else {
            int3.setError(null);
        }

        if (TextUtils.isEmpty(int4.getText().toString().trim()) ||
                Integer.parseInt(int4.getText().toString().trim())>100 ||
                Integer.parseInt(int4.getText().toString().trim())<0) {
            int4.setError("Required.");
            valid = false;
        } else {
            int4.setError(null);
        }

        if (TextUtils.isEmpty(int5.getText().toString().trim()) ||
                Integer.parseInt(int5.getText().toString().trim())>100 ||
                Integer.parseInt(int5.getText().toString().trim())<0) {
            int5.setError("Required.");
            valid = false;
        } else {
            int5.setError(null);
        }

        if (TextUtils.isEmpty(int6.getText().toString().trim()) ||
                Integer.parseInt(int6.getText().toString().trim())>100 ||
                Integer.parseInt(int6.getText().toString().trim())<0) {
            int6.setError("Required.");
            valid = false;
        } else {
            int6.setError(null);
        }

        if (TextUtils.isEmpty(int7.getText().toString().trim()) ||
                Integer.parseInt(int7.getText().toString().trim())>100 ||
                Integer.parseInt(int7.getText().toString().trim())<0) {
            int7.setError("Required.");
            valid = false;
        } else {
            int7.setError(null);
        }

        if (TextUtils.isEmpty(int7.getText().toString().trim()) ||
                Integer.parseInt(int7.getText().toString().trim())>100 ||
                Integer.parseInt(int7.getText().toString().trim())<0) {
            int7.setError("Required.");
            valid = false;
        } else {
            int7.setError(null);
        }

        if (TextUtils.isEmpty(int8.getText().toString().trim()) ||
                Integer.parseInt(int8.getText().toString().trim())>100 ||
                Integer.parseInt(int8.getText().toString().trim())<0) {
            int8.setError("Required.");
            valid = false;
        } else {
            int8.setError(null);
        }

        if (TextUtils.isEmpty(int9.getText().toString().trim()) ||
                Integer.parseInt(int9.getText().toString().trim())>100 ||
                Integer.parseInt(int9.getText().toString().trim())<0) {
            int9.setError("Required.");
            valid = false;
        } else {
            int9.setError(null);
        }

        if (TextUtils.isEmpty(int10.getText().toString().trim()) ||
                Integer.parseInt(int10.getText().toString().trim())>100 ||
                Integer.parseInt(int10.getText().toString().trim())<0) {
            int10.setError("Required.");
            valid = false;
        } else {
            int10.setError(null);
        }

        if (TextUtils.isEmpty(int11.getText().toString().trim()) ||
                Integer.parseInt(int11.getText().toString().trim())>100 ||
                Integer.parseInt(int11.getText().toString().trim())<0) {
            int11.setError("Required.");
            valid = false;
        } else {
            int11.setError(null);
        }

        if (TextUtils.isEmpty(int12.getText().toString().trim()) ||
                Integer.parseInt(int12.getText().toString().trim())>100 ||
                Integer.parseInt(int12.getText().toString().trim())<0) {
            int12.setError("Required.");
            valid = false;
        } else {
            int12.setError(null);
        }

        if (TextUtils.isEmpty(int13.getText().toString().trim()) ||
                Integer.parseInt(int13.getText().toString().trim())>100 ||
                Integer.parseInt(int13.getText().toString().trim())<0) {
            int13.setError("Required.");
            valid = false;
        } else {
            int13.setError(null);
        }

        if (TextUtils.isEmpty(int14.getText().toString().trim()) ||
                Integer.parseInt(int14.getText().toString().trim())>100 ||
                Integer.parseInt(int14.getText().toString().trim())<0) {
            int14.setError("Required.");
            valid = false;
        } else {
            int14.setError(null);
        }

       if (TextUtils.isEmpty(int15.getText().toString().trim()) ||
               Integer.parseInt(int15.getText().toString().trim())>100 ||
               Integer.parseInt(int15.getText().toString().trim())<0) {
           int15.setError("Required.");
           valid = false;
       } else {
           int15.setError(null);
       }

       if (TextUtils.isEmpty(int16.getText().toString().trim()) ||
               Integer.parseInt(int16.getText().toString().trim())>100 ||
               Integer.parseInt(int16.getText().toString().trim())<0) {
           int16.setError("Required.");
           valid = false;
       } else {
           int16.setError(null);
       }

       if (TextUtils.isEmpty(int17.getText().toString().trim()) ||
               Integer.parseInt(int17.getText().toString().trim())>100 ||
               Integer.parseInt(int17.getText().toString().trim())<0) {
           int17.setError("Required.");
           valid = false;
       } else {
           int17.setError(null);
       }

       if (TextUtils.isEmpty(int18.getText().toString().trim()) ||
               Integer.parseInt(int18.getText().toString().trim())>100 ||
               Integer.parseInt(int18.getText().toString().trim())<0) {
           int18.setError("Required.");
           valid = false;
       } else {
           int18.setError(null);
       }

       if (TextUtils.isEmpty(int19.getText().toString().trim()) ||
               Integer.parseInt(int19.getText().toString().trim())>100 ||
               Integer.parseInt(int19.getText().toString().trim())<0) {
           int19.setError("Required.");
           valid = false;
       } else {
           int19.setError(null);
       }

       if (TextUtils.isEmpty(int20.getText().toString().trim()) ||
               Integer.parseInt(int20.getText().toString().trim())>100 ||
               Integer.parseInt(int20.getText().toString().trim())<0) {
           int20.setError("Required.");
           valid = false;
       } else {
           int20.setError(null);
       }

       if (TextUtils.isEmpty(int21.getText().toString().trim()) ||
               Integer.parseInt(int21.getText().toString().trim())>100 ||
               Integer.parseInt(int21.getText().toString().trim())<0) {
           int21.setError("Required.");
           valid = false;
       } else {
           int21.setError(null);
       }

       if (TextUtils.isEmpty(int22.getText().toString().trim()) ||
               Integer.parseInt(int22.getText().toString().trim())>100 ||
               Integer.parseInt(int22.getText().toString().trim())<0) {
           int22.setError("Required.");
           valid = false;
       } else {
           int22.setError(null);
       }

       if (TextUtils.isEmpty(int23.getText().toString().trim()) ||
               Integer.parseInt(int23.getText().toString().trim())>100 ||
               Integer.parseInt(int23.getText().toString().trim())<0) {
           int23.setError("Required.");
           valid = false;
       } else {
           int23.setError(null);
       }
       if (TextUtils.isEmpty(int24.getText().toString().trim()) ||
               Integer.parseInt(int24.getText().toString().trim())>100 ||
               Integer.parseInt(int24.getText().toString().trim())<0) {
           int24.setError("Required.");
           valid = false;
       } else {
           int24.setError(null);
       }

       if (TextUtils.isEmpty(int25.getText().toString().trim()) ||
               Integer.parseInt(int25.getText().toString().trim())>100 ||
               Integer.parseInt(int25.getText().toString().trim())<0) {
           int25.setError("Required.");
           valid = false;
       } else {
           int25.setError(null);
       }


       if ( !dpick1 )
       {
           pick1.setError("Required.");
           valid = false;

       } else {
           pick1.setError(null);
       }

       if ( !dpick2 )
       {
           pick2.setError("Required.");
           valid = false;

       } else {
           pick2.setError(null);
       }

       if ( !dpick3 )
       {
           pick3.setError("Required.");
           valid = false;

       } else {
           pick3.setError(null);
       }

       if ( !dpick4 )
       {
           pick4.setError("Required.");
           valid = false;

       } else {
           pick4.setError(null);
       }

       if ( !dpick5 )
       {
           pick5.setError("Required.");
           valid = false;

       } else {
           pick5.setError(null);
       }

       if ( !dpick6 )
       {
           pick6.setError("Required.");
           valid = false;

       } else {
           pick6.setError(null);
       }

       if ( !dpick7 )
       {
           pick7.setError("Required.");
           valid = false;

       } else {
           pick7.setError(null);
       }
       if ( !dpick8 )
       {
           pick8.setError("Required.");
           valid = false;

       } else {
           pick8.setError(null);
       }

       if ( !dpick9 )
       {
           pick9.setError("Required.");
           valid = false;

       } else {
           pick9.setError(null);
       }

       if ( !dpick10 )
       {
           pick10.setError("Required.");
           valid = false;

       } else {
           pick10.setError(null);
       }

       if ( !dpick11 )
       {
           pick11.setError("Required.");
           valid = false;

       } else {
           pick11.setError(null);
       }

       if ( !dpick12 )
       {
           pick12.setError("Required.");
           valid = false;

       } else {
           pick12.setError(null);
       }

       if ( !dpick13 )
       {
           pick13.setError("Required.");
           valid = false;

       } else {
           pick13.setError(null);
       }

       if ( !dpick14 )
       {
           pick14.setError("Required.");
           valid = false;

       } else {
           pick14.setError(null);
       }

       if ( !dpick15 )
       {
           pick15.setError("Required.");
           valid = false;

       } else {
           pick15.setError(null);
       }
       if ( !dpick16)
       {
           pick16.setError("Required.");
           valid = false;

       } else {
           pick16.setError(null);
       }
       if ( !dpick17 )
       {
           pick17.setError("Required.");
           valid = false;

       } else {
           pick17.setError(null);
       }
       if ( !dpick18 )
       {
           pick18.setError("Required.");
           valid = false;

       } else {
           pick18.setError(null);
       }
       if ( !dpick19 )
       {
           pick19.setError("Required.");
           valid = false;

       } else {
           pick19.setError(null);
       }
       if ( !dpick20 )
       {
           pick20.setError("Required.");
           valid = false;

       } else {
           pick20.setError(null);
       }
       if ( !dpick21)
       {
           pick21.setError("Required.");
           valid = false;

       } else {
           pick21.setError(null);
       }
       if ( !dpick22 )
       {
           pick22.setError("Required.");
           valid = false;

       } else {
           pick22.setError(null);
       }

       if ( !dpick23 )
       {
           pick23.setError("Required.");
           valid = false;

       } else {
           pick23.setError(null);
       }

       if ( !dpick24 )
       {
           pick24.setError("Required.");
           valid = false;

       } else {
           pick24.setError(null);
       }

       if ( !dpick25 )
       {
           pick25.setError("Required.");
           valid = false;

       } else {
           pick25.setError(null);
       }
        return valid;
    }



}