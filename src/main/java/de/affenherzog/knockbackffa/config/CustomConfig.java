package de.affenherzog.knockbackffa.config;

import de.affenherzog.knockbackffa.Kffa;
import java.io.File;
import org.jetbrains.annotations.Nullable;

public abstract class CustomConfig {

  protected final String fileName;

  protected final Kffa kffa;

  public CustomConfig(String fileName) {
    this.fileName = fileName;
    this.kffa = Kffa.getInstance();
  }

  protected void copyFile() {
    if (!new File(kffa.getDataFolder(), "/" + this.fileName).exists()) {
      kffa.saveResource(this.fileName, false);
    }
  }

  public abstract @Nullable Container loadObject();

}
