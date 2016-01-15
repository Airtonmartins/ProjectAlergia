package mpoo.ufrpe.projetoalergia.gui;



import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import mpoo.ufrpe.projetoalergia.R;
import mpoo.ufrpe.projetoalergia.negocio.RemedioNegocio;
import mpoo.ufrpe.projetoalergia.negocio.infra.ProjetoAlergiaException;



public class BarcodeScanner extends AppCompatActivity {

    private static Context contexto;
    private Camera mCamera;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;

    private Button scanButton;
    private ImageScanner scanner;

    private boolean barcodeScanned = false;
    private boolean previewing = true;
    private RemedioNegocio negocio;


    /**
     * Metodo chamado no inicio da activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode_scanner);
        contexto = this;
        initControls();
    }

    /**
     * Metodo que inicializar barcode scanner
     */
    private void initControls() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autoFocusHandler = new Handler();
        try { mCamera = getCameraInstance(); }
        catch (ProjetoAlergiaException e) {
            GuiUtil.showMessage(this,e.getMessage());
        };

        // Instance barcode scanner
        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

        mPreview = new CameraPreview(BarcodeScanner.this, mCamera, previewCb,
                autoFocusCB);
        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview);
        preview.addView(mPreview);

        scanButton = (Button) findViewById(R.id.ScanButton);
        scanButton.setVisibility(View.INVISIBLE);

    }

    /**
     * Metodo que eh invocado assim que pressionamos algum botao
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //se estamos saindo da activity liberamos a camera
            releaseCamera();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Um metodo seguro de retomar a instancia da camera
     */
    public static Camera getCameraInstance() throws ProjetoAlergiaException {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
            throw new ProjetoAlergiaException("Erro abrindo camera!");
        }
        return c;
    }

    /**
     * Metodo criado para liberar a camera fechando a activity
     */
    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
            finish();
        }
    }

    /**
     * Metodo que habilita ajuste automatico de focus
     */
    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };

    /**
     * Metodo que mostra o preview da imagem da camera ate que o codigo seja detectado
     */
    Camera.PreviewCallback previewCb = new Camera.PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {
            Camera.Parameters parameters = camera.getParameters();
            Camera.Size size = parameters.getPreviewSize();

            Image barcode = new Image(size.width, size.height, "Y800");
            barcode.setData(data);

            int result = scanner.scanImage(barcode);

            if (result != 0) {
                previewing = false;
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();

                SymbolSet syms = scanner.getResults();
                for (Symbol sym : syms) {

                    String scanResult = sym.getData().trim();

                    Bundle bundle = new Bundle();
                    bundle.putString("Codigo_de_Barra", scanResult);
                    Intent it = new Intent();
                    it.putExtras(bundle);
                    setResult(RESULT_OK, it);
                    barcodeScanned = true;

                    break;
                }
                releaseCamera();
            }
        }
    };

    // Mimic continuous auto-focusing
    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };

}
