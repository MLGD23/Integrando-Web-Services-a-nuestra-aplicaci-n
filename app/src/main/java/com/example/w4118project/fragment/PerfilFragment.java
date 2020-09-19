package com.example.w4118project.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.w4118project.R;
import com.example.w4118project.adapter.FotoAdapter;
import com.example.w4118project.pojo.Foto;
import com.example.w4118project.pojo.Mascota;
import com.example.w4118project.presenter.IRecyclerViewFragmentPresenter;
import com.example.w4118project.restAPI.ConstantesRestAPI;
import com.example.w4118project.restAPI.EndpointsAPI;
import com.example.w4118project.restAPI.adapter.RestAPIAdapter;
import com.example.w4118project.restAPI.model.MascotaResponse;
import com.example.w4118project.restAPI.model.PerfilResponse;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.w4118project.restAPI.ConstantesRestAPI.KEY_ME;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment implements IRecyclerViewFragmentPresenter {

    private ArrayList<Foto> fotos;
    private RecyclerView listaFotos;
    private TextView tvNombrePerfil;
    private ImageView ivPerfil;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        this.listaFotos = (RecyclerView) view.findViewById(R.id.rv_fotos);
        this.tvNombrePerfil = (TextView) view.findViewById(R.id.tv_nombre_perfil);
        this.ivPerfil = (ImageView) view.findViewById(R.id.iv_perfil);
        this.fotos = new ArrayList<Foto>();

        return view;
    }

    private void obtenerNombrePerfil() {
        RestAPIAdapter adapter = new RestAPIAdapter();
        Gson perfilGSON = adapter.construirGsonDeserializadorPerfil();
        EndpointsAPI endpointsAPI = adapter.establecerConexionRestAPIInstagram(perfilGSON);
        Call<PerfilResponse> perfilResponseCall = endpointsAPI.getPerfil(ConstantesRestAPI.USER_ID.isEmpty() ? ConstantesRestAPI.KEY_ME : ConstantesRestAPI.USER_ID);
        perfilResponseCall.enqueue(new Callback<PerfilResponse>() {
            @Override
            public void onResponse(Call<PerfilResponse> call, Response<PerfilResponse> response) {
                try {
                    PerfilResponse perfilResponse = response.body();

                    String userName = perfilResponse == null ? getResources().getString(R.string.tv_nombre_perfil_text) : perfilResponse.getUserName();
                    int fotoPerfil = perfilResponse == null ? R.drawable.profile_offline : R.drawable.profile;
                    Picasso.get().load(fotoPerfil).into(ivPerfil);
                    tvNombrePerfil.setText(userName);
                }
                catch (Exception e) {
                    Picasso.get().load(R.drawable.profile_offline).into(ivPerfil);
                    Toast.makeText(getContext(), "ERROR en conexion", Toast.LENGTH_LONG).show();
                    Log.e("TAG", "[PerfilFragment.obtenerNombrePerfil()] ERROR en conexion: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<PerfilResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener Nombre de Perfil", Toast.LENGTH_LONG).show();
                Log.e("TAG", "[PerfilFragment.obtenerNombrePerfil()] ERROR en conexion: " + t.toString());
            }
        });
    }

    private void inicializarAdapter() {
        FotoAdapter adapter = new FotoAdapter(this.fotos, getActivity());
        listaFotos.setAdapter(adapter);
    }

    private void inicializarListaFotos() {
        this.fotos = new ArrayList<Foto>();

        Random rand = new Random();
        /*
        this.fotos.add(new Foto(rand.nextInt(11), R.drawable.pet2));
        this.fotos.add(new Foto(rand.nextInt(11), R.drawable.pet2));
        this.fotos.add(new Foto(rand.nextInt(11), R.drawable.pet2));
        this.fotos.add(new Foto(rand.nextInt(11), R.drawable.pet2));
        this.fotos.add(new Foto(rand.nextInt(11), R.drawable.pet2));
        this.fotos.add(new Foto(rand.nextInt(11), R.drawable.pet2));
        this.fotos.add(new Foto(rand.nextInt(11), R.drawable.pet2));
        this.fotos.add(new Foto(rand.nextInt(11), R.drawable.pet2));
        this.fotos.add(new Foto(rand.nextInt(11), R.drawable.pet2));
        this.fotos.add(new Foto(rand.nextInt(11), R.drawable.pet2));
        this.fotos.add(new Foto(rand.nextInt(11), R.drawable.pet2));
        this.fotos.add(new Foto(rand.nextInt(11), R.drawable.pet2));
         */

    }

    @Override
    public void obtenerMascotasDB() {

    }

    @Override
    public void obtenerMediosRecientes() {
        RestAPIAdapter adapter = new RestAPIAdapter();
        Gson recentMediaGSON = adapter.construirGsonDeserializadorRecentMedia();
        EndpointsAPI endpointsAPI = adapter.establecerConexionRestAPIInstagram(recentMediaGSON);
        Call<MascotaResponse> mascotaResponseCall = endpointsAPI.getRecentMedia(ConstantesRestAPI.USER_ID.isEmpty() ? ConstantesRestAPI.KEY_ME : ConstantesRestAPI.USER_ID);
        //Call<ContactoResponse> contactoResponseCall = endpointsAPI.testURL();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                try {
                    fotos.clear();
                    MascotaResponse mascotaResponse = response.body();
                    ArrayList<Mascota> mascotas = mascotaResponse == null ? new ArrayList<>() : mascotaResponse.getMascotas();

                    Log.i("TAG", "[PerfilFragment.obtenerMediosRecientes()] response.body(): " + response.body());
                    Log.i("TAG", "[PerfilFragment.obtenerMediosRecientes()] mascotaResponse: " + mascotaResponse);
                    Log.i("TAG", "[PerfilFragment.obtenerMediosRecientes()] mascotas: " + mascotas);

                    for (Mascota m : mascotas) {
                        Foto fotoActual = new Foto(m.getRating(), m.getUrlFoto());
                        fotos.add(fotoActual);
                    }

                    mostrarMascotaRV();
                    //forcing exception thrown if user not found:
                    mascotaResponse.getMascotas();
                }
                catch (Exception e) {
                    Toast.makeText(getContext(), "ERROR en conexion", Toast.LENGTH_LONG).show();
                    Log.e("TAG", "[PerfilFragment.obtenerMediosRecientes()] ERROR en conexion: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener fotos recientes", Toast.LENGTH_LONG).show();
                Log.e("TAG", "[PerfilFragment.obtenerMediosRecientes()] ERROR en conexion: " + t.toString());
            }
        });
    }

    @Override
    public void mostrarMascotaRV() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);

        this.listaFotos.setLayoutManager(glm);

        inicializarAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();

        obtenerNombrePerfil();
        obtenerMediosRecientes();
    }
}