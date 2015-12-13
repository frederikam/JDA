package net.dv8tion.jda.handle;

import net.dv8tion.jda.JDA;
import net.dv8tion.jda.entities.Guild;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Michael Ritter on 13.12.2015.
 */
public class ReadyHandler implements ISocketHandler
{
    private final JDA api;
    private final EntityBuilder builder;

    public ReadyHandler(JDA api)
    {
        this.api = api;
        this.builder = new EntityBuilder(api);
    }

    @Override
    public void handle(JSONObject content)
    {
        builder.createSelfInfo(content.getJSONObject("user"));
//            JSONArray priv_chats = content.getJSONArray("private_channels");
//            for (int i = 0; i < priv_chats.length(); i++)
//            {
//                api.getPrivChannels().add(EntityBuilder.createPrivateChannel(priv_chats.getJSONObject(i)));
//            }
        JSONArray guilds = content.getJSONArray("guilds");
        for (int i = 0; i < guilds.length(); i++)
        {
            Guild g = builder.createGuild(guilds.getJSONObject(i));
//            api.getGuildMap().put(g.getId(), g);
        }
    }
}