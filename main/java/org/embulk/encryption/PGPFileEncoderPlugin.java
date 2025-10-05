package org.embulk.encryption;

import org.embulk.config.ConfigSource;
import org.embulk.config.TaskSource;
import org.embulk.spi.EncoderPlugin;
import org.embulk.spi.FileOutput;
import org.embulk.util.config.Config;
import org.embulk.util.config.ConfigDefault;
import org.embulk.util.config.ConfigMapper;
import org.embulk.util.config.ConfigMapperFactory;
import org.embulk.util.config.Task;

public class PGPFileEncoderPlugin implements EncoderPlugin
{
    private static final ConfigMapperFactory CONFIG_MAPPER_FACTORY = ConfigMapperFactory.withDefault();

    public interface PluginTask extends Task
    {
        @Config("passphrase")
        String getPassphrase();

        @Config("ascii_armor")
        @ConfigDefault("true")
        boolean getAsciiArmor();

        @Config("with_compression")
        @ConfigDefault("true")
        boolean getWithCompression();
    }

    @Override
    public void transaction(ConfigSource config, EncoderPlugin.Control control)
    {
        final ConfigMapper configMapper = CONFIG_MAPPER_FACTORY.createConfigMapper();
        final PluginTask task = configMapper.map(config, PluginTask.class);
        control.run(task.toTaskSource());
    }

    @Override
    public FileOutput open(TaskSource taskSource, FileOutput output)
    {
        final PluginTask task = CONFIG_MAPPER_FACTORY.createTaskMapper().map(taskSource, PluginTask.class);
        return output;
    }
}
