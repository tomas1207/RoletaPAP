package com.example.tomas1207portable.roletapap;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CustomRoleta.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CustomRoleta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomRoleta extends Fragment {


    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;
    Button Save;
    EditText text1;
    EditText text2;
    EditText text3;
    EditText text4;
    String TextOn1;
    String TextOn2;
    String TextOn3;
    String TextOn4;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public CustomRoleta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomRoleta.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomRoleta newInstance(String param1, String param2) {
CustomRoleta fragment = new CustomRoleta();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_custam_roleta,container,false);
        text4 = (EditText)v.findViewById(R.id.QuarGameTXT);
        text3 = (EditText)v.findViewById(R.id.TerGameTXT);
        text2 = (EditText)v.findViewById(R.id.SecondGameTXT);
        text1 = (EditText)v.findViewById(R.id.FristGameTXT);
         sharedPreferences = this.getActivity().getSharedPreferences("cenas", Context.MODE_PRIVATE);

        Save = (Button)v.findViewById(R.id.SaveBNT);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextOn1 = text1.getText().toString();

                TextOn2 = text2.getText().toString();

                TextOn3 = text3.getText().toString();

                TextOn4 = text4.getText().toString();
                editor = sharedPreferences.edit()
                        .putString("text1",TextOn1)
                        .putString("text2",TextOn2)
                        .putString("text3",TextOn3)
                        .putString("text4",TextOn4);
                editor.apply();

            }
        });
        return v;
            }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String string);
    }
}
